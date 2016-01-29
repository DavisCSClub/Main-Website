package org.dcsc.athena.objects;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.dcsc.athena.objects.Person;
import java.util.ArrayList;
import java.util.HashSet;
//Long live Axis.
public class AxisQueue {

    private ArrayList<Person> tutorQueue;
    private ArrayList<Person> tuteeQueue;

    private final Object lock = new Object();

    public AxisQueue() {
        tutorQueue = new ArrayList<Person>();
        tuteeQueue = new ArrayList<Person>();
    }

    public void pairAttempt(Person t) {
        synchronized (lock) {



            if (t.isTutor()) {

                if (tuteeQueue.size() != 0) {
                    //There are people to be tutored

                    HashSet<String> cachedTutorSubjects = t.getSubjects();

                    Iterator<Person> peopleIterator = tuteeQueue.iterator();

                    while (peopleIterator.hasNext()) {
                        Person p = peopleIterator.next();
                        if (cachedTutorSubjects.contains(p.getSubject())) {
                            peopleIterator.remove();
                            //TODO: More processing
                            break;
                        }
                    }
                } else {
                    //Auto-add to waiting list (tutee)
                }

            } else {

                if (tutorQueue.size() != 0) {
                    //There are people that might be able to tutor you

                    String cachedTuteeSubject = t.getSubject();

                    Iterator<Person> peopleIterator = tutorQueue.iterator();

                    while (peopleIterator.hasNext()) {
                        Person p = peopleIterator.next();
                        if (p.getSubjects().contains(cachedTuteeSubject)) {
                            peopleIterator.remove();
                            //TODO: More processing
                            break;
                        }
                    }


                } else {
                    //Auto-add to waiting list (tutee)
                }

            }





        }
    }

}