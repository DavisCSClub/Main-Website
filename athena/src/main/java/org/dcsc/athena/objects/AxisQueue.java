package org.dcsc.athena.objects;

import org.dcsc.athena.objects.TutoringSession;

import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.stereotype.Component;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class AxisQueue {

	private ConcurrentHashMap<String, Subject> tutoredSubjects;

	private ConcurrentHashMap<TutorExtension, Boolean>  currentTutors;

    private ConcurrentHashMap<Tutee, Boolean>  currentTutees;

    private ConcurrentHashMap<Person, String>  personToId;

    private ConcurrentHashMap<String, Person>  idToPerson;
	
    private ConcurrentHashMap<String, TutoringSession> tutorSessions;

    private ConcurrentHashMap<Long, Boolean> currentIdTutors;

    private final boolean DEBUG_MODE = true;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

	//Todo
    private ArrayList<String> orderedSubjects(TutorExtension tr) {
    	return new ArrayList<String>(tr.getSubjects());
    }

    public AxisQueue() {
    	tutoredSubjects = new ConcurrentHashMap<String, Subject>();
        currentTutors = new ConcurrentHashMap<TutorExtension, Boolean>();
        currentTutees = new ConcurrentHashMap<Tutee, Boolean>();
        personToId = new ConcurrentHashMap<Person, String> ();
        idToPerson = new ConcurrentHashMap<String, Person> ();
        tutorSessions = new ConcurrentHashMap<String, TutoringSession> ();
        currentIdTutors = new ConcurrentHashMap<Long, Boolean>();
    }


    private void addClass(String className) {
    	tutoredSubjects.put(className, new Subject(className));
    }

    public boolean hasId(long id) {
        return currentIdTutors.containsKey(id);
    }

    public void setSession(String id, TutoringSession s) {
        // currentIdTutors.put(s.getTutor().getId(), true);
        tutorSessions.put(id, s);
    }

    public TutoringSession getSession(String id) {
        return tutorSessions.get(id);
    }

    public TutoringSession popSession(String id) {
        TutoringSession s = tutorSessions.get(id);

        if(s != null) {
            tutorSessions.remove(id);
        }
        return s;
    }

    private void addTutorToClass(TutorExtension tr, String subject) {
    	tutoredSubjects.get(subject).addTutor(tr);
    }

	private void addTuteeToClass(Tutee te, String subject) {
		tutoredSubjects.get(subject).addTutee(te);
    }    

    private boolean canTutor(TutorExtension tr) {
    	for (String subject : orderedSubjects(tr)) {
    		if (tutoredSubjects.containsKey(subject) && tutoredSubjects.get(subject).getBalance() < 0) {
    			tr.setTutorSubject(subject);
    			return true;
    		}
    	}
    	return false;
    }

    private boolean canBeTutored(String subjectRequested) {
    	return tutoredSubjects.containsKey(subjectRequested) && tutoredSubjects.get(subjectRequested).getBalance() > 0;
    }


    private Status removeTutor(TutorExtension tr, String subject) {
        Subject s = tutoredSubjects.get(subject);
        if (s == null)
            return new Status(StatusType.ERROR, "Tutor not present");

        Status retStatus = s.removeTutor(tr);
        //Todo: Notify channel if no tutors avaliable
    	return retStatus;
    }

    private Status removeTutee(Tutee te, String subject) {
    	return tutoredSubjects.get(subject).removeTutee(te);
    }

    private Status processTutor(TutorExtension tr) {
        Status currentStatus = tutoredSubjects.get(tr.getTutorSubject()).processTutor(tr);
        if (currentStatus.getType().equals(StatusType.TUTEE_FOUND)) {
            currentTutees.remove(currentStatus.getTutee());
            removeCurrentTutor(tr.getTutor().getId());
            messagingTemplate.convertAndSend("/topic/" + currentStatus.getTutee().getRoom(), tr.getPairingData());
        }
        
    	return currentStatus;
    }

    private Status processTutee(Tutee te, String classRequested) {
    	Status currentStatus = tutoredSubjects.get(classRequested).processTutee(te);
    	
    	TutorExtension pairedTutor = currentStatus.getTutor();

        if (currentStatus.getType().equals(StatusType.TUTOR_FOUND)) {
            currentTutors.remove(currentStatus.getTutor());
            messagingTemplate.convertAndSend("/topic/" + currentStatus.getTutor().getRoom(), te.getPairingData());
        }

    	if (currentStatus.getType() != StatusType.ERROR) {
    		for (String  subject : pairedTutor.getSubjects()) {
    			removeTutor(pairedTutor, subject);
    		}
            removeCurrentTutor(pairedTutor.getTutor().getId());
    	}
    	
    	return currentStatus;
    }

    public synchronized HashMap<String, Integer> getQueueData() {
        if (DEBUG_MODE) {
            
            HashMap<String, Integer> temp = new HashMap<String, Integer>();
            temp.put("20", 501);
            return temp;
        }

        //TODO: Do a bookkeeping method rather than dynamic generation? 
        HashMap<String, Integer> queueData = new HashMap<String, Integer>() ;
        for (Subject subject : tutoredSubjects.values()) {
            queueData.put(subject.getName(), subject.getQueueSize());
        }
        return queueData;
    }

    public synchronized ArrayList<HashMap<String, String>> getTutorList() {
        if (DEBUG_MODE) {
    	    ArrayList<HashMap<String, String>> temp = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> temp2 = new HashMap<String, String>();
            temp2.put("subjects", "10, 20, 30");
            temp2.put("tutorID", "5124");
            temp2.put("name", "WORK IN PROGRESS");
            temp2.put("location", "PC 31");
            temp.add(temp2);
            return temp;
        }
        return new ArrayList<HashMap<String, String>>();
    }

    public synchronized Status tutorQuit(TutorExtension tr) {
    	for (String  subject : tr.getSubjects()) {
    		removeTutor(tr, subject);
    	}

    	currentTutors.remove(tr);
        System.out.println("TUTOR QUITTTT\n\n\n\n\n\n\n\n");
        
        removeCurrentTutor(tr.getTutor().getId());

    	return new Status(StatusType.TUTOR_QUIT, tr);
    }

    public synchronized void removeCurrentTutor(Long id) {
        currentIdTutors.remove(id);
    }

    public synchronized Status removePersonAndMappingByID(String id) {
        Person p = idToPerson.get(id);
        if (p != null) {
            if (p instanceof TutorExtension) {
                TutorExtension tr = (TutorExtension) p;
                if (tr.getValid())
                    removeCurrentTutor(tr.getTutor().getId());
                tutorQuit(tr);
            } else {
                Tutee te = (Tutee) p;
                tuteeQuit(te);
            }    
        }
        removePersonMapping(id);
        return new Status(StatusType.ERROR, "ID " + id + " not found");
    }

    public synchronized Status tuteeQuit(Tutee te) {
        currentTutees.remove(te);
    	removeTutee(te, te.getRequestedClass());
    	return new Status(StatusType.TUTEE_QUIT, te);
    }

    // public synchronized Status tutorPresent(Tutor tr) {
    // 	currentTutors.put(tr, true);
    // 	return new Status(StatusType.TUTOR_REGISTERED, tr);
    // }

    public synchronized Status add(Person p) {

    	if (p instanceof TutorExtension) {

    		TutorExtension tr = (TutorExtension) p;
            if (currentTutors.containsKey(tr) || hasId(tr.getTutor().getId())) {
                return new Status(StatusType.DUPE_ERROR, "Duplicate Tutor");
            }

            currentIdTutors.put(tr.getTutor().getId(), true);
            tr.setValid(true);

    		if(canTutor(tr)) {
    			return processTutor(tr);
    		} else {
                currentTutors.put(tr, true);
    			for (String subject : tr.getSubjects()) {
    				if (!tutoredSubjects.containsKey(subject)) {
    					addClass(subject);
    				}
    				addTutorToClass(tr, subject);
    			}
    			return new Status(StatusType.TUTOR_ADDED, tr);
    		}
    	} else if (p instanceof Tutee) {
    		Tutee te = (Tutee) p;
    		String classRequested = te.getRequestedClass();

            if (currentTutees.containsKey(te))
                return new Status(StatusType.DUPE_ERROR, "Duplicate Tutee");
    		if(canBeTutored(classRequested)) {
    			return processTutee(te, classRequested);
    		} else {
                currentTutees.put(te, true);
    			if (!tutoredSubjects.containsKey(classRequested)) {
    				addClass(classRequested);
    			}
    			addTuteeToClass(te, classRequested);

    			return new Status(StatusType.TUTEE_ADDED, te);
    		}
    	} else {
    		System.out.println("[QueueError] Person not Tutor or Tutee");
    	}

    	return new Status();
    }

    //Could probably move to its own file
    public synchronized void addPersonMapping(Person t, String mapping) {
        personToId.put(t, mapping);
        idToPerson.put(mapping, t);
    }

    public synchronized Person getPersonFromID(String mapping) {
        return idToPerson.get(mapping);
    }
    public synchronized void removePersonMapping(Person t) {
        String mapping = personToId.remove(t);
        if (mapping != null)
            idToPerson.remove(mapping) ;
    }

    public synchronized void removePersonMapping(String mapping) {
        Person t = idToPerson.remove(mapping);
  
        if (t != null)
            personToId.remove(t) ;
    }

    /* ============ Debug Functions ============ */

    public synchronized int debugGetQueueBalance(String subject) {
    	if (tutoredSubjects.containsKey(subject))
    		return tutoredSubjects.get(subject).getBalance();
    	return -1;
    }

    public synchronized String queueStatus() {
        String retString = "";
        for (String key : tutoredSubjects.keySet()) {
            retString += key + " " + Integer.toString(tutoredSubjects.get(key).getNumTutors()) + " " + Integer.toString(tutoredSubjects.get(key).getQueueSize()) + "\n";
        }
        return retString;
    }
}