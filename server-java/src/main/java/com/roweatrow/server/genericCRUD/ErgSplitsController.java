package com.roweatrow.server.genericCRUD;

import com.roweatrow.server.models.ErgSplit;
import com.roweatrow.server.respository.ErgSplitRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/ergSplits")
public class ErgSplitsController {
private final ErgSplitRepository ergSplitRepository;

public CRUDController(
        ErgSplitRepository ergSplitRepository
        ){
        this.ergSplitRepository=ergSplitRepository;
        }

@GetMapping(value = "/{ergSplitId}")
public @ResponseBody ErgSplit getErgSplit(@PathVariable Long ergSplitId){
        Optional<ErgSplit> ergSplit=ergSplitRepository.findById(ergSplitId);
        return ergSplit.orElse(null);
        }

@PostMapping(value = "")
public @ResponseBody ErgSplit createErgSplit(@RequestBody ErgSplit requestBody){
        if(requestBody.getErgSplit()!=null){
        Optional<ErgSplit> ergSplit=ergSplitRepository.findById(requestBody.getErgSplit());

        if(ergSplit.isPresent()){
        return ergSplit.get();
        }
        }

        return ergSplitRepository.save(requestBody);
        }
        }
