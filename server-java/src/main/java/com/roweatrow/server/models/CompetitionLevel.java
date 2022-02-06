package com.roweatrow.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "competition_level")
public class CompetitionLevel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnoreProperties(ignoreUnknown = true)
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
