package com.roweatrow.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "shell")
public class Shell {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnoreProperties(ignoreUnknown = true)
  @Column(name = "shell")
  private Long shell;

  @Column(name = "name")
  private String name;

  @Column(name = "team")
  private Long team;

  public Long getShell() {
    return this.shell;
  }

  public void setShell(Long shell) {
    this.shell = shell;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getTeam() {
    return this.team;
  }

  public void setTeam(Long team) {
    this.team = team;
  }
}
