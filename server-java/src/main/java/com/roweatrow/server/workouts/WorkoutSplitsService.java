package com.roweatrow.server.workouts;

import com.roweatrow.server.models.*;
import com.roweatrow.server.respository.*;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutSplitsService {

  private final WorkoutRepository workoutRepository;
  private final SplitRepository splitRepository;

  public WorkoutSplitsService(
      WorkoutRepository workoutRepository, SplitRepository splitRepository) {
    this.workoutRepository = workoutRepository;
    this.splitRepository = splitRepository;
  }

  public <T extends Split> List<T> addSplit(Workout<T> w, T split) {
    List<T> splits = w.getSplits();
    long seq = 0;

    if (!splits.isEmpty()) {
      T finalSplit = splits.get(splits.size() - 1);
      seq = finalSplit.getSeq() + 1;
    }

    split.setSeq(seq);
    split.setWorkout(w);
    splitRepository.save(split);

    splits.add(split);
    return splits;
  }

  public <T extends Split> List<T> updateSeq(Workout<T> w, T split, Long newSeq) {
    List<T> splits = w.getSplits();
    // TODO: Realistically seq should be an int.
    int leftSplit = (int) Math.min(split.getSeq(), newSeq);
    int rightSplit = (int) Math.max(split.getSeq(), newSeq);
    List<T> shiftedSplits;

    if (newSeq < split.getSeq()) {
      shiftedSplits = splits.subList(leftSplit + 1, rightSplit + 1);
      for (Split s : shiftedSplits) {
        s.setSeq(s.getSeq() - 1);
      }
      shiftedSplits.add(split);
    } else {
      shiftedSplits = splits.subList(leftSplit, rightSplit);
      for (Split s : shiftedSplits) {
        s.setSeq(s.getSeq() + 1);
      }
      shiftedSplits.add(0, split);
    }
    split.setSeq(newSeq);

    for (Split s : shiftedSplits) {
      splitRepository.save(s);
    }

    return shiftedSplits;
  }

  public <T extends Workout> TemplateWorkout createTemplateWorkoutFromSplits(Workout<? extends Split> w, Long team) {
    List<? extends Split> splits = w.getSplits();
    List<TemplateSplit> templateSplits =
        splits.stream()
            .map(
                s ->
                    TemplateSplit.builder()
                        .duration(s.getDuration())
                        .distance(s.getDistance())
                        .seq(s.getSeq())
                        .build())
            .collect(Collectors.toList());

    TemplateWorkout templateWorkout =
        TemplateWorkout.builder()
            .team(team)
            .name(w.getName())
            .templateSplits(templateSplits)
            .build();

    return workoutRepository.save(templateWorkout);
  }

  public ErgWorkout assignAsErgWorkout(AssignedWorkout w, Long athleteId, Date date) {
    List<TemplateSplit> splits = w.getSplits();
    List<ErgSplit> ergSplits =
        splits.stream()
            .map(
                s ->
                    ErgSplit.builder()
                        .duration(s.getDuration())
                        .distance(s.getDistance())
                        .seq(s.getSeq())
                        .build())
            .collect(Collectors.toList());

    ErgWorkout ergWorkout =
        ErgWorkout.builder()
            .assignedWorkout(w.getAssignedWorkout())
            .athlete(athleteId)
            .date(date)
            .ergSplits(ergSplits)
            .build();
    return workoutRepository.save(ergWorkout);
  }

  public WaterWorkout assignAsWaterWorkout(AssignedWorkout w, Boat b, Date date) {
    List<TemplateSplit> splits = w.getSplits();
    List<Long> athletes = b.getAthletes();
    List<WaterSplit> waterSplits =
        splits.stream()
            .map(
                s -> {
                  List<WaterWorkoutAthleteSplit> waterWorkoutAthleteSplits =
                      athletes.stream()
                          .map(a -> WaterWorkoutAthleteSplit.builder().athlete(a).build())
                          .collect(Collectors.toList());
                  return WaterSplit.builder()
                      .duration(s.getDuration())
                      .distance(s.getDistance())
                      .seq(s.getSeq())
                      .waterWorkoutAthleteSplit(waterWorkoutAthleteSplits)
                      .build();
                })
            .collect(Collectors.toList());

    WaterWorkout waterWorkout =
        WaterWorkout.builder()
            .assignedWorkout(w.getAssignedWorkout())
            .boat(b.getBoat())
            .waterSplits(waterSplits)
            .date(date)
            .build();
    return workoutRepository.save(waterWorkout);
  }
}
