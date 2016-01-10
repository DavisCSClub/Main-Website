package org.dcsc.core.time;

import javax.persistence.*;

@Entity
@Table(name = "dcsc_academic_term", schema = "dcsc_time")
public class AcademicTerm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "code")
    private String code;

    @Column(name = "year")
    private int year;

    public AcademicTerm() {
    }

    public AcademicTerm(long id, String code, int year) {
        this.id = id;
        this.code = code;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString() {
        return String.format("%s %d", AcademicTermNames.expandTermCode(code), year);
    }
}
