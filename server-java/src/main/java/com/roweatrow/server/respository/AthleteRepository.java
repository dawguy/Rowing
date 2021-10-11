package com.roweatrow.server.respository;

import com.roweatrow.server.model.Athlete;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AthleteRepository extends CrudRepository<Athlete, Long> {
    List<Athlete> findAthleteByName(String name);
}
