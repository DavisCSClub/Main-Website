package org.dcsc.athena.objects;

public abstract class Person {

    protected String email;
    protected String name;
    protected String location;
    private   String room;

    public String getName() {
    	return name;
    }

    public String getEmail() {
    	return email;
    }

    public void setRoom(String room) {
    	this.room = room;
    }

    public String getRoom() {
    	return room;
    }

    public String getLocation() {
        return location;
    }

    public abstract boolean equals(Object other);
}