package com.roweatrow.server.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CompetitionLevel {
    @Id
    private long competitionLevel;

    private int level;
}
