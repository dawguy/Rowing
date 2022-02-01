package com.roweatrow.server.models;

import javax.persistence.*;

@Entity
@Table(name = "competition_level")
public class CompetitionLevel {
    @Id
    @Column(name = "competition_level")
    private Long competitionLevel;

    @Column(name = "level")
    private Long level;

    public Long getCompetitionLevel() {
        return this.competitionLevel;
    }

    public void setCompetitionLevel(Long competitionLevel) {
        this.competitionLevel = competitionLevel;
    }

    public Long getLevel() {
        return this.level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }
}
