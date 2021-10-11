package com.roweatrow.server.respository;

import com.roweatrow.server.model.Boat;
import org.springframework.data.repository.CrudRepository;

public interface BoatRepository extends CrudRepository<Boat, Long> {
}
