package com.roweatrow.server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "erg_workout")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true, value = {})
public class ErgWorkout implements Workout<ErgSplit> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "erg_workout")
  private Long ergWorkout;

  @Column(name = "date")
  private java.sql.Date date;

  @Column(name = "athlete")
  private Long athlete;

  @Column(name = "assigned_workout")
  private Long assignedWorkout;

  @OneToMany(mappedBy = "ergWorkout", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("seq")
  private List<ErgSplit> ergSplits = new ArrayList<>();

  // https://stackoverflow.com/questions/9533676/jpa-onetomany-parent-child-reference-foreign-key
  @PrePersist
  private void prePersist() {
    if (ergSplits != null) {
      ergSplits.forEach(s -> s.setErgWorkout(this));
    }
  }

  public Long getWorkout() {
    return this.ergWorkout;
  }

  public Long getErgWorkout() {
    return this.ergWorkout;
  }

  public void setErgWorkout(Long ergWorkout) {
    this.ergWorkout = ergWorkout;
  }

  public java.sql.Date getDate() {
    return this.date;
  }

  public void setDate(java.sql.Date date) {
    this.date = date;
  }

  public Long getAthlete() {
    return this.athlete;
  }

  public void setAthlete(Long athlete) {
    this.athlete = athlete;
  }

  public Long getAssignedWorkout() {
    return this.assignedWorkout;
  }

  public void setAssignedWorkout(Long assignedWorkout) {
    this.assignedWorkout = assignedWorkout;
  }

  public List<ErgSplit> getSplits() {
    return this.ergSplits;
  }

  public String getName() {
    return ""; // TODO: Do later
  }
}
