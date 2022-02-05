package com.roweatrow.server.genericCRUD;

import com.roweatrow.server.models.WaterWorkout;
import com.roweatrow.server.respository.WaterWorkoutRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/waterWorkouts")
public class WaterWorkoutsController {
private final WaterWorkoutRepository waterWorkoutRepository;

public CRUDController(
        WaterWorkoutRepository waterWorkoutRepository
        ){
        this.waterWorkoutRepository=waterWorkoutRepository;
        }

@GetMapping(value = "/{waterWorkoutId}")
public @ResponseBody WaterWorkout getWaterWorkout(@PathVariable Long waterWorkoutId){
        Optional<WaterWorkout> waterWorkout=waterWorkoutRepository.findById(waterWorkoutId);
        return waterWorkout.orElse(null);
        }

@PostMapping(value = "")
public @ResponseBody WaterWorkout createWaterWorkout(@RequestBody WaterWorkout requestBody){
        if(requestBody.getWaterWorkout()!=null){
        Optional<WaterWorkout> waterWorkout=waterWorkoutRepository.findById(requestBody.getWaterWorkout());

        if(waterWorkout.isPresent()){
        return waterWorkout.get();
        }
        }

        return waterWorkoutRepository.save(requestBody);
        }
        }
