package com.roweatrow.server.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Shell {
    @Id
    private long shell;

    private String name;

    @ManyToOne
    private School school;
}
