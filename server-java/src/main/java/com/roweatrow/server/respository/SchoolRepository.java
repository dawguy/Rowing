package com.roweatrow.server.respository;

import com.roweatrow.server.model.School;
import org.springframework.data.repository.CrudRepository;

public interface SchoolRepository extends CrudRepository<School, Long> {
}
