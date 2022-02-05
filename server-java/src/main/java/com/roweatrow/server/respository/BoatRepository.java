package com.roweatrow.server.respository;

import com.roweatrow.server.models.Boat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoatRepository extends CrudRepository<Boat, Long> {

    @Query(nativeQuery = true,
            value = "SELECT boat FROM Boat boat " +
            "LEFT JOIN boat.seat_1 seat_1 " +
            "LEFT JOIN boat.seat_2 seat_2 " +
            "LEFT JOIN boat.seat_3 seat_3 " +
            "LEFT JOIN boat.seat_4 seat_4 " +
            "LEFT JOIN boat.seat_5 seat_5 " +
            "LEFT JOIN boat.seat_6 seat_6 " +
            "LEFT JOIN boat.seat_7 seat_7 " +
            "LEFT JOIN boat.seat_8 seat_8 "
    )
    public List<Boat> retrieveAll();

    @Query(nativeQuery = true,
            value = "SELECT boat.* FROM boat boat WHERE ?1 IN (seat_1, seat_2, seat_3, seat_4, seat_5, seat_6, seat_7, seat_8)"
    )
    public List<Boat> retrieveAllByAthleteId(long athleteId);
}
