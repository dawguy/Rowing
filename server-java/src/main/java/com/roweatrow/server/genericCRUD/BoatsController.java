package com.roweatrow.server.genericCRUD;

import com.roweatrow.server.models.Boat;
import com.roweatrow.server.respository.BoatRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/boats")
public class BoatsController {
    private final BoatRepository boatRepository;

    public BoatsController(
            BoatRepository boatRepository
    ) {
        this.boatRepository = boatRepository;
    }

    @GetMapping(value = "/{boatId}")
    public @ResponseBody
    Boat getBoat(@PathVariable Long boatId) {
        Optional<Boat> boat = boatRepository.findById(boatId);
        return boat.orElse(null);
    }

    @PostMapping(value = "")
    public @ResponseBody
    Boat createBoat(@RequestBody Boat requestBody) {
        if (requestBody.getBoat() != null) {
            Optional<Boat> boat = boatRepository.findById(requestBody.getBoat());

            if (boat.isPresent()) {
                return boat.get();
            }
        }

        return boatRepository.save(requestBody);
    }
}
