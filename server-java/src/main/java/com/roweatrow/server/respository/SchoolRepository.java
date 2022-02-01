package com.roweatrow.server.respository;

import com.roweatrow.server.models.School;
import org.springframework.data.repository.CrudRepository;

public interface SchoolRepository extends CrudRepository<School, Long> {
}
