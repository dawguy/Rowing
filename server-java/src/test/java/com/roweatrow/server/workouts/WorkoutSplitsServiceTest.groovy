package com.roweatrow.server.workouts

import com.roweatrow.server.models.ErgSplit
import com.roweatrow.server.models.ErgWorkout
import com.roweatrow.server.models.Split
import com.roweatrow.server.models.TemplateSplit
import com.roweatrow.server.models.TemplateWorkout
import com.roweatrow.server.models.WaterSplit
import com.roweatrow.server.models.WaterWorkout
import com.roweatrow.server.models.Workout
import com.roweatrow.server.respository.SplitRepository
import com.roweatrow.server.respository.WorkoutRepository
import org.mockito.InjectMocks
import org.mockito.Mock
import spock.lang.Specification
import spock.lang.Subject

class WorkoutSplitsServiceTest extends Specification {
    @Subject WorkoutSplitsService workoutSplitsService
    @Mock WorkoutRepository workoutRepository
    @Mock SplitRepository splitRepository

    def setup(){
        workoutRepository = Mock(WorkoutRepository.class)
        splitRepository = Mock(SplitRepository.class)
        workoutSplitsService = new WorkoutSplitsService(workoutRepository, splitRepository)
    }

    def "AddSplit ergSplit"(ErgSplit split, Workout workout, long expectedSeq) {
        expect:
        int workoutLength = workout.getSplits().size();
        List<? extends Split> updatedSplits = workoutSplitsService.addSplit(workout, split)
        updatedSplits.size() == workoutLength + 1
        Split finalSplit = updatedSplits.last()
        finalSplit.seq == expectedSeq

        where:
        split          | workout                                                                 | expectedSeq
        new ErgSplit() | new ErgWorkout(ergSplits: [new ErgSplit(seq: 0)])                       | 1
        new ErgSplit() | new ErgWorkout(ergSplits: [new ErgSplit(seq: 0), new ErgSplit(seq: 1)]) | 2
        new ErgSplit() | new ErgWorkout(ergSplits: [])                                           | 0
    }

    def "AddSplit waterSplit"(WaterSplit split, Workout workout, long expectedSeq) {
        expect:
        int workoutLength = workout.getSplits().size();
        List<? extends Split> updatedSplits = workoutSplitsService.addSplit(workout, split)
        updatedSplits.size() == workoutLength + 1
        Split finalSplit = updatedSplits.last()
        finalSplit.seq == expectedSeq

        where:
        split            | workout                                                                         | expectedSeq
        new WaterSplit() | new WaterWorkout(waterSplits: [new WaterSplit(seq: 0)])                         | 1
        new WaterSplit() | new WaterWorkout(waterSplits: [new WaterSplit(seq: 0), new WaterSplit(seq: 1)]) | 2
        new WaterSplit() | new WaterWorkout(waterSplits: [])                                               | 0
    }

    def "AddSplit templateSplit"(TemplateSplit split, Workout workout, long expectedSeq) {
        expect:
        int workoutLength = workout.getSplits().size();
        List<? extends Split> updatedSplits = workoutSplitsService.addSplit(workout, split)
        updatedSplits.size() == workoutLength + 1
        Split finalSplit = updatedSplits.last()
        finalSplit.seq == expectedSeq

        where:
        split               | workout                                                                                     | expectedSeq
        new TemplateSplit() | new TemplateWorkout(templateSplits: [new TemplateSplit(seq: 0)])                            | 1
        new TemplateSplit() | new TemplateWorkout(templateSplits: [new TemplateSplit(seq: 0), new TemplateSplit(seq: 1)]) | 2
        new TemplateSplit() | new TemplateWorkout(templateSplits: [])                                                     | 0
    }
}
