package com.roweatrow.server.workouts;

import com.roweatrow.server.models.*;
import com.roweatrow.server.respository.*;

import java.util.List;
import java.util.stream.Collectors;

public class WorkoutSplitsService {

    private final WorkoutRepository workoutRepository;
    private final SplitRepository splitRepository;

    public WorkoutSplitsService(
            WorkoutRepository workoutRepository,
            SplitRepository splitRepository
    ){
        this.workoutRepository = workoutRepository;
        this.splitRepository = splitRepository;
    }

    // TODO: Determine if Workout's getSplits needs to be updated.
    public <T extends Split> List<T> addSplit(Workout<T> w, T split){
        List<T> splits = w.getSplits();
        long seq = 0;

        if(!splits.isEmpty()){
            Split finalSplit = splits.get(splits.size()-1);
            seq = finalSplit.getSeq() + 1;
        }

        split.setSeq(seq);
        split.setWorkout(w.getWorkout());
        splitRepository.save(split);

        splits.add(split);
        return splits;
    }

    // TODO: Determine if Workout's getSplits needs to be updated.
    public <T extends Split> List<T> updateSeq(Workout<T> w, T split, Long newSeq){
        List<T> splits = w.getSplits();
        // TODO: Realistically seq should be an int.
        int leftSplit = (int)Math.min(split.getSeq(), newSeq);
        int rightSplit = (int)Math.max(split.getSeq(), newSeq);
        List<T> shiftedSplits;

        if(newSeq < split.getSeq()){
            shiftedSplits = splits.subList(leftSplit+1, rightSplit+1);
            for(Split s : shiftedSplits){
                s.setSeq(s.getSeq() - 1);
            }
            shiftedSplits.add(split);
        } else {
            shiftedSplits = splits.subList(leftSplit, rightSplit);
            for(Split s : shiftedSplits){
                s.setSeq(s.getSeq() + 1);
            }
            shiftedSplits.add(0, split);
        }
        split.setSeq(newSeq);

        for(Split s : shiftedSplits){
            splitRepository.save(s);
        }

        return shiftedSplits;
    }

    public TemplateWorkout cloneWorkoutSplitsToTemplateSplits(Workout w, Long team){
        List<Split> splits = w.getSplits();
        List<TemplateSplit> templateSplits = splits.stream().map(s ->
                TemplateSplit.builder()
                        .duration(s.getDuration())
                        .distance(s.getDistance())
                        .seq(s.getSeq())
                        .build()
        ).collect(Collectors.toList());

        TemplateWorkout templateWorkout = TemplateWorkout.builder()
                .team(team)
                .name(w.getName())
                .templateSplits(templateSplits)
                .build();

        TemplateWorkout savedWorkout = workoutRepository.save(templateWorkout);
        //TODO: Test if the templateSplits were auto created.

        return savedWorkout;
    }

    public List<ErgSplit> cloneToErgSplits(Workout<? extends Split> w){
        List<? extends Split> splits = w.getSplits();
        return splits.stream().map(s ->
            ErgSplit.builder()
                    .duration(s.getDuration())
                    .distance(s.getDistance())
                    .seq(s.getSeq())
                    .build()
        ).collect(Collectors.toList());
    }

    public List<Split> cloneToWaterSplits(Workout<? extends Split> w, Boat b){
        List<? extends Split> splits = w.getSplits();
        List<Long> athletes = b.getAthletes();
        return splits.stream().map(s -> {
            List<WaterWorkoutAthleteSplit> waterWorkoutAthleteSplits = athletes.stream().map(a -> WaterWorkoutAthleteSplit.builder().athlete(a).build()).collect(Collectors.toList());
            return WaterSplit.builder()
                    .duration(s.getDuration())
                    .distance(s.getDistance())
                    .seq(s.getSeq())
                    .waterWorkoutAthleteSplit(waterWorkoutAthleteSplits)
                    .build();
        }).collect(Collectors.toList());
    }
}
