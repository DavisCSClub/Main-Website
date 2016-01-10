package org.dcsc.core.time;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Comparator;

@RunWith(MockitoJUnitRunner.class)
public class AcademicTermComparatorTest {
    private static final Comparator comparator = new AcademicTermComparator();

    @Mock
    private AcademicTerm academicTerm1;
    @Mock
    private AcademicTerm academicTerm2;

    @Test
    public void firstParameterWithEarlierYear() {
        Mockito.when(academicTerm1.getYear()).thenReturn(2013);
        Mockito.when(academicTerm2.getYear()).thenReturn(2015);

        Assert.assertTrue(comparator.compare(academicTerm1, academicTerm2) < 0);
    }

    @Test
    public void secondParameterWithEarlierYear() {
        Mockito.when(academicTerm1.getYear()).thenReturn(2015);
        Mockito.when(academicTerm2.getYear()).thenReturn(2013);

        Assert.assertTrue(comparator.compare(academicTerm1, academicTerm2) > 0);
    }

    @Test
    public void firstParameterWithEarlierTerm() {
        Mockito.when(academicTerm1.getYear()).thenReturn(2015);
        Mockito.when(academicTerm2.getYear()).thenReturn(2015);
        Mockito.when(academicTerm1.getCode()).thenReturn("FQ");
        Mockito.when(academicTerm2.getCode()).thenReturn("SS2");

        Assert.assertTrue(comparator.compare(academicTerm1, academicTerm2) < 0);
    }

    @Test
    public void secondParameterWithEarlierTerm() {
        Mockito.when(academicTerm1.getYear()).thenReturn(2015);
        Mockito.when(academicTerm2.getYear()).thenReturn(2015);
        Mockito.when(academicTerm1.getCode()).thenReturn("SS2");
        Mockito.when(academicTerm2.getCode()).thenReturn("FQ");

        Assert.assertTrue(comparator.compare(academicTerm1, academicTerm2) > 0);
    }

    @Test
    public void sameTerm() {
        Mockito.when(academicTerm1.getYear()).thenReturn(2015);
        Mockito.when(academicTerm2.getYear()).thenReturn(2015);
        Mockito.when(academicTerm1.getCode()).thenReturn("WQ");
        Mockito.when(academicTerm2.getCode()).thenReturn("WQ");

        Assert.assertTrue(comparator.compare(academicTerm1, academicTerm2) == 0);
    }
}