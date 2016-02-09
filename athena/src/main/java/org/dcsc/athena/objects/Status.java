package org.dcsc.athena.objects;
import java.util.HashMap;

public class Status
{
	private StatusType statusType;
	private String textStatus;
	private HashMap<String, String> statusMap
	;
	private TutorExtension tutor;
	private Tutee tutee;
	public Status(StatusType status, String textStatus) {
		this(status);
		this.statusType = status;
		this.textStatus = textStatus;
		
	}

	public Status(StatusType status, TutorExtension tutor) {
		this(status);
		this.tutor = tutor;

		
	}

	public Status(StatusType status, Tutee tutee) {
		this(status);
		this.tutee = tutee;		
	}

	public Status(StatusType status, TutorExtension tutor, Tutee tutee) {
		this(status);

		this.tutee = tutee;
		this.tutor = tutor;
		statusMap.put("TutorName", tutor.getName());
		statusMap.put("TutorLocation", tutor.getLocation());

		statusMap.put("TutorURL", tutor.getURL());

		statusMap.put("TuteeName", tutee.getName());
		statusMap.put("TuteeLocation", tutee.getLocation());
	}

	public Status(StatusType status) {
		this.statusType = status;
		this.textStatus = "None";
		statusMap = new HashMap<String, String>();
        statusMap.put("StatusType", statusType.toString());
	}

	public Status() {
		
	}

	public StatusType getType() {
		return statusType;
	}

	public TutorExtension getTutor() {
		return tutor;
	}

	public Tutee getTutee() {
		return tutee;
	}

	public HashMap<String, String> statusData() {
		return statusMap;
		// if (!this.textStatus.equals("None"))
		// 	return this.statusType.toString() + " - " + this.textStatus;
		// return this.statusType.toString();
	}
	
	@Override 
	public boolean equals(Object other) {
    	return (other instanceof Status && ((Status)other).getType().equals(this.getType()));
	}
}