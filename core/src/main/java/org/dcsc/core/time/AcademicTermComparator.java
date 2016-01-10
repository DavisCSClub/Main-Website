package org.dcsc.core.time;

import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class AcademicTermComparator implements Comparator<AcademicTerm> {
    @Override
    public int compare(AcademicTerm o1, AcademicTerm o2) {
        int year1 = o1.getYear();
        int year2 = o2.getYear();

        if (year1 == year2) {
            int order1 = AcademicTermNames.ALL.indexOf(o1.getCode());
            int order2 = AcademicTermNames.ALL.indexOf(o2.getCode());

            return order1 - order2;
        } else if (year1 < year2) {
            return -1;
        } else {
            return 1;
        }
    }
}
