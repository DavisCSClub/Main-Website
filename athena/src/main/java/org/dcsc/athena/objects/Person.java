package org.dcsc.athena.objects;
import java.util.HashSet;

public class Person {

	private Integer id;
    private String type;
    private String email;
    private String location;
    private HashSet<String> tutee_tutored_subjects;
    private String tutee_desired_subject;

    public Person() {
        
    }

    public boolean isTutor() {
    	return false;
    }

    public boolean isTutee() {
    	return false;
    }

    public HashSet<String> getSubjects() {
    	return null;
    }

    public String getSubject() {
    	return null;
    }
}