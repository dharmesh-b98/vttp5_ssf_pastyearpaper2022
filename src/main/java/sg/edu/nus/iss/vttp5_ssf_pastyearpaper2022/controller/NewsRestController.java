package sg.edu.nus.iss.vttp5_ssf_pastyearpaper2022.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.vttp5_ssf_pastyearpaper2022.constants.Constant;
import sg.edu.nus.iss.vttp5_ssf_pastyearpaper2022.repo.HashRepo;

@RestController
@RequestMapping(path = "/news", produces="application/json")
public class NewsRestController {
    
    @Autowired
    HashRepo newsRepo;

    @GetMapping(path = "/{id}", produces = "application/json")
    ResponseEntity<String> getsavedNews(@PathVariable("id") String id){
        if (newsRepo.hasKey(Constant.redisKey, id)){
            String jsonString = (String) newsRepo.get(Constant.redisKey, id);
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(jsonString);
        }
        else{
            JsonObjectBuilder job = Json.createObjectBuilder();
            String jsonString = job.add("error", "cannot find article"+id).build().toString();
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(jsonString);
        }

    }
}
