package com.roweatrow.server.genericCRUD;

import com.roweatrow.server.models.WaterWorkoutAthleteSplit;
import com.roweatrow.server.respository.WaterWorkoutAthleteSplitRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/waterWorkoutAthleteSplits")
public class WaterWorkoutAthleteSplitsController {
private final WaterWorkoutAthleteSplitRepository waterWorkoutAthleteSplitRepository;

public CRUDController(
        WaterWorkoutAthleteSplitRepository waterWorkoutAthleteSplitRepository
        ){
        this.waterWorkoutAthleteSplitRepository=waterWorkoutAthleteSplitRepository;
        }

@GetMapping(value = "/{waterWorkoutAthleteSplitId}")
public @ResponseBody WaterWorkoutAthleteSplit getWaterWorkoutAthleteSplit(@PathVariable Long waterWorkoutAthleteSplitId){
        Optional<WaterWorkoutAthleteSplit> waterWorkoutAthleteSplit=waterWorkoutAthleteSplitRepository.findById(waterWorkoutAthleteSplitId);
        return waterWorkoutAthleteSplit.orElse(null);
        }

@PostMapping(value = "")
public @ResponseBody WaterWorkoutAthleteSplit createWaterWorkoutAthleteSplit(@RequestBody WaterWorkoutAthleteSplit requestBody){
        if(requestBody.getWaterWorkoutAthleteSplit()!=null){
        Optional<WaterWorkoutAthleteSplit> waterWorkoutAthleteSplit=waterWorkoutAthleteSplitRepository.findById(requestBody.getWaterWorkoutAthleteSplit());

        if(waterWorkoutAthleteSplit.isPresent()){
        return waterWorkoutAthleteSplit.get();
        }
        }

        return waterWorkoutAthleteSplitRepository.save(requestBody);
        }
        }
