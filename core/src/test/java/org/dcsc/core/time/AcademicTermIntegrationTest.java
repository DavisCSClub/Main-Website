package org.dcsc.core.time;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AcademicTermIntegrationTest {
    private static final Comparator comparator = new AcademicTermComparator();
    private List<AcademicTerm> orderedAcademicTerms = new ArrayList<>();

    @Before
    public void before() {
        int length = AcademicTermNames.ALL.size();
        for (int i = 0; i < length; i++) {
            AcademicTerm term = new AcademicTerm(i, AcademicTermNames.ALL.get(i), 2015 + i);
            orderedAcademicTerms.add(term);
        }
    }

    @Test
    public void sort() {
        List<AcademicTerm> academicTerms = new ArrayList();

        int length = AcademicTermNames.ALL.size();
        for (int i = length - 1; i >= 0; i--) {
            academicTerms.add(orderedAcademicTerms.get(i));
        }

        academicTerms.sort(comparator);

        for (int i = 0; i < length; i++) {
            Assert.assertEquals(orderedAcademicTerms.get(i), academicTerms.get(i));
        }
    }
}
