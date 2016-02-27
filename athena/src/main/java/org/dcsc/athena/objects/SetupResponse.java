package org.dcsc.athena.objects;
import java.util.*;

public class SetupResponse {

    private ArrayList<HashMap<String, String>> tutorData;
    private HashMap<String, Integer> queueData;
    private String myID;

    public SetupResponse(ArrayList<HashMap<String, String>> tutorData, HashMap<String, Integer> queueData, String myID) {
        this.tutorData = tutorData;
        this.queueData = queueData;
        this.myID = myID;
    }

    public ArrayList<HashMap<String, String>> getTutorData() {
        return tutorData;
    }

    public HashMap<String, Integer> getQueueData() {
        return queueData;
    }

    public String getMyID() {
        return myID;
    }
}