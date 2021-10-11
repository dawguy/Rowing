package com.roweatrow.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CompetitionLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long competitionLevel;

    private int level;

    public CompetitionLevel() {
    }
}
