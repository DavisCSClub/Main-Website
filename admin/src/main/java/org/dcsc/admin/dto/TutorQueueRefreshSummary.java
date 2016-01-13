package org.dcsc.admin.dto;

import java.util.List;

public class TutorQueueRefreshSummary {
    List<QueueSummary> summaries;

    public TutorQueueRefreshSummary(List<QueueSummary> summaries) {
        this.summaries = summaries;
    }

    public List<QueueSummary> getSummaries() {
        return summaries;
    }

    public void setSummaries(List<QueueSummary> summaries) {
        this.summaries = summaries;
    }
}
