package com.roweatrow.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class CompetitionLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long competitionLevel;

    private int level;

    public CompetitionLevel() {
    }
}
