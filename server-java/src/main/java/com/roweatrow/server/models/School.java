package com.roweatrow.server.models;

import javax.persistence.*;

@Entity
@Table(name = "school")
public class School {
    @Id
    @Column(name = "school")
    private Long school;

    @Column(name = "name")
    private String name;

    public Long getSchool() {
        return this.school;
    }

    public void setSchool(Long school) {
        this.school = school;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
