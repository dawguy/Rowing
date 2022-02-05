package com.roweatrow.server.genericCRUD;

import com.roweatrow.server.models.ErgWorkout;
import com.roweatrow.server.respository.ErgWorkoutRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/ergWorkouts")
public class ErgWorkoutsController {
private final ErgWorkoutRepository ergWorkoutRepository;

public CRUDController(
        ErgWorkoutRepository ergWorkoutRepository
        ){
        this.ergWorkoutRepository=ergWorkoutRepository;
        }

@GetMapping(value = "/{ergWorkoutId}")
public @ResponseBody ErgWorkout getErgWorkout(@PathVariable Long ergWorkoutId){
        Optional<ErgWorkout> ergWorkout=ergWorkoutRepository.findById(ergWorkoutId);
        return ergWorkout.orElse(null);
        }

@PostMapping(value = "")
public @ResponseBody ErgWorkout createErgWorkout(@RequestBody ErgWorkout requestBody){
        if(requestBody.getErgWorkout()!=null){
        Optional<ErgWorkout> ergWorkout=ergWorkoutRepository.findById(requestBody.getErgWorkout());

        if(ergWorkout.isPresent()){
        return ergWorkout.get();
        }
        }

        return ergWorkoutRepository.save(requestBody);
        }
        }
