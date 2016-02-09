package org.dcsc.athena.objects;
import java.util.HashMap;
import org.dcsc.athena.objects.StatusType;

public class Tutee extends Person{

	private String requestedClass;
    private HashMap<String, String> tuteeMapping;

    public Tutee(String name, String email, String requestedClass, String id, String location) {
    	this.name = name;
    	this.email = email;
    	this.requestedClass = requestedClass;
        this.location = location;
        tuteeMapping = new HashMap<String, String>();
        tuteeMapping.put("TuteeName", name);
        tuteeMapping.put("TuteeLocation", location);
        tuteeMapping.put("StatusType", StatusType.TUTEE_FOUND.toString());
        setRoom(id);
    }
      
    public String getRequestedClass() {
    	return requestedClass;
    }

    public HashMap<String, String> getPairingData() {
        return tuteeMapping;
    }


    @Override 
	public boolean equals(Object other) {
    	return (other instanceof Tutee && ((Tutee)other).getEmail().equals(this.getEmail()) && ((Tutee)other).getRoom().equals(this.getRoom()));
	}
}