package org.dcsc.athena.objects;

import java.util.TreeSet;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.AttributeConverter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)
public class AxisQueueTest {
    // private static final AxisQueue axisQueue = new AxisQueue();

    @Test
    public void testInsertionTutor() {
        // TreeSet<String> subjectList = new TreeSet<String>();
        // subjectList.add("30");
        // subjectList.add("40");
        // subjectList.add("50");

        // Tutor t = new Tutor("Jane Doe", "jdoe@ucdavis.edu", subjectList);
        
        // Status s = axisQueue.add(t);
        // Assert.assertEquals(s.getType(), StatusType.TUTOR_ADDED);
    }

    // @Test
    // public void testInsertionTutee() {
        
    //     Tutee t = new Tutee("Jane Doer", "jdoer@ucdavis.edu", "30");
        
    //     Status s = axisQueue.add(t);
    //     Assert.assertEquals(s.getType(), StatusType.TUTEE_ADDED);
    // }

    // @Test
    // public void testPairing() {

    //     TreeSet<String> subjectList = new TreeSet<String>();
    //     subjectList.add("30");
    //     subjectList.add("40");
    //     subjectList.add("50");

    //     Tutor t = new Tutor("Jane Doe", "jdoe@ucdavis.edu", subjectList);

    //     Status s = axisQueue.add(t);

    //     Assert.assertEquals(s.getType(), StatusType.TUTOR_ADDED);

    //     Assert.assertEquals(axisQueue.debugGetQueueBalance("30"), 1);
    //     Assert.assertEquals(axisQueue.debugGetQueueBalance("40"), 1);
    //     Assert.assertEquals(axisQueue.debugGetQueueBalance("50"), 1);

    //     Tutee r = new Tutee("Jane Doer", "jdoer@ucdavis.edu", "30");
        
    //     Status z = axisQueue.add(r);
        
    //     Assert.assertEquals(z.getType(), StatusType.TUTOR_FOUND);        

    //     Assert.assertEquals(axisQueue.debugGetQueueBalance("30"), 0);
    //     Assert.assertEquals(axisQueue.debugGetQueueBalance("40"), 0);
    //     Assert.assertEquals(axisQueue.debugGetQueueBalance("50"), 0);

    //     Status q = axisQueue.add(r);

    //     Assert.assertEquals(q.getType(), StatusType.TUTEE_ADDED);

    //     Assert.assertEquals(axisQueue.debugGetQueueBalance("30"), -1);
    //     Assert.assertEquals(axisQueue.debugGetQueueBalance("40"), 0);
    //     Assert.assertEquals(axisQueue.debugGetQueueBalance("50"), 0);

    //     Assert.assertEquals(1, 2);

    // }

}