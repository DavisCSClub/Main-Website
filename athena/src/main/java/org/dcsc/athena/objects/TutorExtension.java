package org.dcsc.athena.objects;

import java.util.TreeSet;
import java.util.HashMap;

public class TutorExtension extends Person{

	private TreeSet<String> subjects;
	private String tutoredSubject;
    private HashMap<String, String> tutorMapping;
    private String imgURL;

    public TreeSet<String> getSubjects() {
    	return subjects;
    }
    
    public void setTutorSubject(String subject) {
    	tutoredSubject = subject;
    }

	public String getTutorSubject() {
    	return tutoredSubject;
    }

    public String getURL() {
        return imgURL;
    }

    public HashMap<String, String> getPairingData() {
        return tutorMapping;
    }

    public TutorExtension(String name, String email, TreeSet<String> subjects, String id) {
    	this.name = name;
    	this.email = email;
    	this.subjects = subjects;
        this.location = "N/A";
        this.imgURL = "http://placehold.it/150x150";
        setRoom(id);
        tutorMapping = new HashMap<String, String>();
        tutorMapping.put("TutorName", name);
        tutorMapping.put("TutorLocation", location);
        tutorMapping.put("TutorURL", imgURL);
        tutorMapping.put("StatusType", StatusType.TUTOR_FOUND.toString());
    }
    
	@Override 
	public boolean equals(Object other) {
    	return (other instanceof TutorExtension && ((TutorExtension)other).getEmail().equals(this.getEmail()) && ((TutorExtension)other).getRoom().equals(this.getRoom()));
	}

    @Override
    public String toString() {
        return this.name;
    }
}