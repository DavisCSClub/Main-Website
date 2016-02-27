package org.dcsc.athena.objects;

import java.util.concurrent.*;

public class Subject {

    private String name;

    private ConcurrentLinkedDeque<TutorExtension> tutorQueue;
    private ConcurrentLinkedDeque<Tutee> tuteeQueue;

    public Subject() {
		tutorQueue = new ConcurrentLinkedDeque<TutorExtension>();
		tuteeQueue = new ConcurrentLinkedDeque<Tutee>();
	}

	public Subject(String name) {
		this.name = name;
		tutorQueue = new ConcurrentLinkedDeque<TutorExtension>();
		tuteeQueue = new ConcurrentLinkedDeque<Tutee>();	
	}
    
    public String getName() {
        return name;
    }

    //Balance is the number of tutors to tutees
    public int getBalance() {
    	return tutorQueue.size() - tuteeQueue.size();
    }
    
    public int getQueueSize() {
        return tuteeQueue.size();
    }

    public int getNumTutors() {
        return tutorQueue.size();
    }

    public Status addTutor(TutorExtension tr) {
    	tutorQueue.push(tr);
    	return new Status(StatusType.TUTOR_ADDED, tr);
    }

    public Status addTutee(Tutee te) {
    	tuteeQueue.push(te);
    	return new Status(StatusType.TUTEE_ADDED, te);
    }

	public Status removeTutor(TutorExtension tr) {
    	if (tutorQueue.remove(tr))
    		return new Status(StatusType.TUTEE_ADDED, tr);

    	return new Status(StatusType.TUTOR_NOT_FOUND, tr);
    }

    public Status removeTutee(Tutee te) {
    	if (tuteeQueue.remove(te))
    		return new Status(StatusType.TUTEE_ADDED, te);	

    	return new Status(StatusType.TUTEE_NOT_FOUND, te);
    }

    public Status processTutor(TutorExtension tr) {
    	if (tuteeQueue.size() == 0) 
    		return new Status(StatusType.ERROR, "No tutees avaliable. Possible threading issue.");

        Tutee te = tuteeQueue.poll();
 
    	return new Status(StatusType.TUTEE_FOUND, tr, te);
    }

    public Status processTutee(Tutee te) {
    	if (tutorQueue.size() == 0) 
    		return new Status(StatusType.ERROR, "No tutors avaliable. Possible threading issue.");
     
    	TutorExtension tr = tutorQueue.poll();
  
    	return new Status(StatusType.TUTOR_FOUND, tr, te);
    }
}