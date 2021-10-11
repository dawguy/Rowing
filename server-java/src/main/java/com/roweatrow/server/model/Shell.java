package com.roweatrow.server.model;

import javax.persistence.*;

@Entity
public class Shell {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long shell;

    private String name;

    @ManyToOne
    private School school;
}
