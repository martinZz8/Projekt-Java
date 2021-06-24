package com.example.backend.controller;

import com.example.backend.DTO.AdministratorDTOI;
import com.example.backend.DTO.AdministratorDTOO;
import com.example.backend.DTO.AdministratorVerifyO;
import com.example.backend.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="administrator")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdministratorController {
    private final AdministratorService administratorService;

    @Autowired
    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<AdministratorDTOO> getAllAdministrators()
    {
        return administratorService.getAllAdministrators();
    }

    @GetMapping(path = "/{administratorId}", produces = "application/json")
    public Optional<AdministratorDTOO> getAdministrator(@PathVariable String administratorId)
    {
        try
        {
            return administratorService.getAdministrator(Long.parseLong(administratorId));
        }
        catch(NumberFormatException e)
        {
            return Optional.empty();
        }
    }

    @PostMapping(path="/verify", produces = "application/json")
    public ResponseEntity<AdministratorVerifyO> postVerifyAdministrator(@RequestBody AdministratorDTOI administratorDTOI)
    {
        AdministratorVerifyO ret = administratorService.postVerifyAdministrator(administratorDTOI);
        if(ret.getRet_code()==1)
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(ret);
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ret);
    }

    @PostMapping(path="/add")
    public ResponseEntity<String> addAdministrator(@RequestBody AdministratorDTOI administratorDTOI)
    {
        if(administratorService.addAdministrator(administratorDTOI))
            return ResponseEntity.status(HttpStatus.CREATED).body("Success");
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure");
    }

    @DeleteMapping(path="/delete/{administratorId}")
    public ResponseEntity<String> deleteAdministrator(@PathVariable String administratorId)
    {
        try
        {
            if(administratorService.deleteAdministrator(Long.parseLong(administratorId)))
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            else
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure");
        }
        catch(NumberFormatException e)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure");
        }
    }

    @PutMapping(path="/update_password", produces = "application/json")
    public ResponseEntity<String> updatePasswordOfAdministrator(@RequestParam(name = "email", required = true) String email, @RequestParam(name="password", required = true) String new_password)
    {
        Integer ret = administratorService.updatePasswordOfAdministrator(email, new_password);
        if(ret==2)
            return ResponseEntity.status(HttpStatus.CREATED).body("Success");
        else if (ret==1)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure - the same password was given");
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure - user with given email doesn't exist");
    }
}
