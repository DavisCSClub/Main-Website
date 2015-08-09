package org.dcsc.officers;

import javax.persistence.*;

/**
 * Created by tktong on 8/3/2015.
 */
@Entity
@Table(name = "dcsc_officers", schema = "public")
public class DcscOfficer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "start_term")
    @Enumerated(EnumType.STRING)
    private DcscOfficerTerm startTerm;

    @Column(name = "end_term")
    @Enumerated(EnumType.STRING)
    private DcscOfficerTerm endTerm;

    @Column(name = "year")
    private int year;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DcscOfficerTerm getStartTerm() {
        return startTerm;
    }

    public void setStartTerm(DcscOfficerTerm startTerm) {
        this.startTerm = startTerm;
    }

    public DcscOfficerTerm getEndTerm() {
        return endTerm;
    }

    public void setEndTerm(DcscOfficerTerm endTerm) {
        this.endTerm = endTerm;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
