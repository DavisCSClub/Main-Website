package org.dcsc.core.time;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class AcademicTermNames {
    public static final String FALL_QUARTER = "FQ";
    public static final String WINTER_QUARTER = "WQ";
    public static final String SPRING_QUARTER = "SQ";
    public static final String SUMMER_SESSION_1 = "SS1";
    public static final String SUMMER_SESSION_2 = "SS2";

    public static final List<String> ALL = ImmutableList.of(FALL_QUARTER, WINTER_QUARTER, SPRING_QUARTER, SUMMER_SESSION_1, SUMMER_SESSION_2);
    public static final List<String> FULL_NAMES = ImmutableList.of("Fall Quarter", "Winter Quarter", "Spring Quarter", "Summer Session 1", "Summer Session 2");

    public static String expandTermCode(String code) {
        return FULL_NAMES.get(ALL.indexOf(code));
    }
}
