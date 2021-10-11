package com.roweatrow.server.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class School {
    @Id
    private long school;
    private String name;
}
