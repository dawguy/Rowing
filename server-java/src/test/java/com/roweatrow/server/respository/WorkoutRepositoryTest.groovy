package com.roweatrow.server.respository

import com.roweatrow.server.models.AssignedWorkout
import com.roweatrow.server.models.ErgWorkout
import com.roweatrow.server.models.TemplateWorkout
import com.roweatrow.server.models.WaterWorkout
import spock.lang.Specification
import spock.lang.Subject

class WorkoutRepositoryTest extends Specification {

    @Subject
    private WorkoutRepository workoutRepository;

    private ErgWorkoutRepository ergWorkoutRepository;
    private WaterWorkoutRepository waterWorkoutRepository;
    private TemplateWorkoutRepository templateWorkoutRepository;
    private AssignedWorkoutRepository assignedWorkoutRepository;

    def setup(){
        ergWorkoutRepository = Mock()
        waterWorkoutRepository = Mock(WaterWorkoutRepository.class)
        templateWorkoutRepository = Mock()
        assignedWorkoutRepository = Mock()

        workoutRepository = new WorkoutRepository(ergWorkoutRepository,waterWorkoutRepository,templateWorkoutRepository,assignedWorkoutRepository)
    }

    def "Save WaterWorkoutRepository"() {
        given:
        WaterWorkout ww = new WaterWorkout()

        when:
        workoutRepository.save(ww)

        then:
        1 * waterWorkoutRepository.save(ww)
    }

    def "Save TemplateWorkoutRepository"() {
        given:
        TemplateWorkout e = new TemplateWorkout()

        when:
        workoutRepository.save(e)

        then:
        1 * templateWorkoutRepository.save(e)
    }

    def "Save AssignedWorkoutRepository"() {
        given:
        AssignedWorkout e = new AssignedWorkout()

        when:
        workoutRepository.save(e)

        then:
        1 * assignedWorkoutRepository.save(e)
    }

    def "Save ergWorkout"() {
        given:
        ErgWorkout e = new ErgWorkout()

        when:
        workoutRepository.save(e)

        then:
        1 * ergWorkoutRepository.save(e)
    }
}
