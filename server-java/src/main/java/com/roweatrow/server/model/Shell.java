package com.roweatrow.server.model;

import javax.persistence.*;

@Entity
@Embeddable
public class Shell {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long shell;

    private String name;

    @ManyToOne
    @JoinColumn(name = "school")
    private School school;
}
