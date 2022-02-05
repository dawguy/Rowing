package com.roweatrow.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "athlete")
public class Athlete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Column(name = "athlete")
    private Long athlete;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private java.sql.Date birthday;

    public Long getAthlete() {
        return this.athlete;
    }

    public void setAthlete(Long athlete) {
        this.athlete = athlete;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.sql.Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(java.sql.Date birthday) {
        this.birthday = birthday;
    }
}
