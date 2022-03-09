package com.roweatrow.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "assigned_workout")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssignedWorkout implements Workout<TemplateSplit> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "assigned_workout")
  private Long assignedWorkout;

  @Column(name = "date")
  private java.sql.Date date;

  @Column(name = "name")
  private String name;

  @Column(name = "team")
  private Long team;

  @ManyToOne()
  @JoinColumn(name = "template_workout", insertable = false, updatable = false)
  private TemplateWorkout templateWorkout;

  public Long getAssignedWorkout() {
    return this.assignedWorkout;
  }

  public Long getWorkout() {
    return this.assignedWorkout;
  }

  public void setAssignedWorkout(Long assignedWorkout) {
    this.assignedWorkout = assignedWorkout;
  }

  public java.sql.Date getDate() {
    return this.date;
  }

  public void setDate(java.sql.Date date) {
    this.date = date;
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

  public TemplateWorkout getTemplateWorkout() {
    return this.templateWorkout;
  }

  public void setTemplateWorkout(TemplateWorkout templateWorkout) {
    this.templateWorkout = templateWorkout;
  }

  public List<TemplateSplit> getSplits() {
    return this.templateWorkout != null ? this.templateWorkout.getSplits() : null;
  }
}
