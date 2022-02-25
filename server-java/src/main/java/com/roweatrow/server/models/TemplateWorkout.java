package com.roweatrow.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "template_workout")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateWorkout implements Workout<TemplateSplit> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnoreProperties(ignoreUnknown = true)
  @Column(name = "template_workout")
  private Long templateWorkout;

  @Column(name = "name")
  private String name;

  @Column(name = "team")
  private Long team;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "template_workout", nullable = false)
  @OrderBy("seq")
  private List<TemplateSplit> templateSplits = new ArrayList<>();

  public Long getWorkout() {
    return this.templateWorkout;
  }

  public Long getTemplateWorkout() {
    return this.templateWorkout;
  }

  public void setTemplateWorkout(Long templateWorkout) {
    this.templateWorkout = templateWorkout;
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

  public List<TemplateSplit> getSplits() {
    return this.templateSplits;
  }
}
