package com.roweatrow.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Embeddable
@Getter
@Setter
public class Shell {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long shell;

    private String name;

    @ManyToOne
    @JoinColumn(name = "school")
    private School school;
}
