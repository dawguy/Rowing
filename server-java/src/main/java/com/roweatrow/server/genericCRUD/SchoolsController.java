package com.roweatrow.server.genericCRUD;

import com.roweatrow.server.models.School;
import com.roweatrow.server.respository.SchoolRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/schools")
public class SchoolsController {
private final SchoolRepository schoolRepository;

public CRUDController(
        SchoolRepository schoolRepository
        ){
        this.schoolRepository=schoolRepository;
        }

@GetMapping(value = "/{schoolId}")
public @ResponseBody School getSchool(@PathVariable Long schoolId){
        Optional<School> school=schoolRepository.findById(schoolId);
        return school.orElse(null);
        }

@PostMapping(value = "")
public @ResponseBody School createSchool(@RequestBody School requestBody){
        if(requestBody.getSchool()!=null){
        Optional<School> school=schoolRepository.findById(requestBody.getSchool());

        if(school.isPresent()){
        return school.get();
        }
        }

        return schoolRepository.save(requestBody);
        }
        }
