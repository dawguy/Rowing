package com.roweatrow.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "school")
public class School {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnoreProperties(ignoreUnknown = true)
  @Column(name = "school")
  private Long school;

  @Column(name = "name")
  private String name;

  public Long getSchool() {
    return this.school;
  }

  public void setSchool(Long school) {
    this.school = school;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
