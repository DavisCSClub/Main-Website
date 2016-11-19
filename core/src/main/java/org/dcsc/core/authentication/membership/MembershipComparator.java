package org.dcsc.core.authentication.membership;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MembershipComparator implements Comparator<Membership> {
    private static final Map<String, Integer> OFFICER_TITLES = new HashMap<>();

    public MembershipComparator() {
        OFFICER_TITLES.put("President", 0);
        OFFICER_TITLES.put("Vice-President of External Affairs", 1);
        OFFICER_TITLES.put("Vice-President of Internal Affairs", 1);
        OFFICER_TITLES.put("Treasurer", 2);
        OFFICER_TITLES.put("Secretary", 3);
        OFFICER_TITLES.put("Marketing Director", 4);
        OFFICER_TITLES.put("Events Coordinator", 5);
        OFFICER_TITLES.put("Game Development Committee Chair", 6);
        OFFICER_TITLES.put("Game Development Committee Vice-Chair", 7);
        OFFICER_TITLES.put("Pragmatic Programming Committee Chair", 6);
        OFFICER_TITLES.put("Pragmatic Programming Committee Vice-Chair", 7);
        OFFICER_TITLES.put("Professional Development Committee Chair", 6);
        OFFICER_TITLES.put("Professional Development Committee Vice-Chair", 7);
        OFFICER_TITLES.put("Tutoring Committee Chair", 6);
        OFFICER_TITLES.put("Tutoring Committee Vice-Chair", 7);
        OFFICER_TITLES.put("Web Development Committee Chair", 6);
        OFFICER_TITLES.put("Web Development Committee Vice-Chair", 7);
    }

    @Override
    public int compare(Membership o1, Membership o2) {
        int order1 = getOrderFromTitle(o1.getTitle());
        int order2 = getOrderFromTitle(o2.getTitle());
        return order1 - order2;
    }

    private int getOrderFromTitle(String title) {
        return OFFICER_TITLES.containsKey(title) ? OFFICER_TITLES.get(title) : 100;
    }
}
