package com.example.backend.service;

import com.example.backend.DTO.AdministratorDTOI;
import com.example.backend.DTO.AdministratorDTOO;
import com.example.backend.model.Administrator;
import com.example.backend.repositories.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService {
    private final AdministratorRepository administratorRepository;

    @Autowired
    public AdministratorService(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    public List<AdministratorDTOO> getAllAdministrators() {
        List<Administrator> act_admins = administratorRepository.findAll();
        List<AdministratorDTOO> ret_admins = new ArrayList<>();
        for(Administrator act_admin : act_admins)
        {
            AdministratorDTOO ret_admin = new AdministratorDTOO(act_admin);
            ret_admins.add(ret_admin);
        }
        return ret_admins;
    }

    public Optional<AdministratorDTOO> getAdministrator(Long administratorId) {
        Optional<Administrator> optional_admin = administratorRepository.findById(administratorId);
        if(optional_admin.isPresent())
        {
            AdministratorDTOO ret_admin = new AdministratorDTOO(optional_admin.get());
            return Optional.of(ret_admin);
        }
        else
        {
            return Optional.empty();
        }
    }

    public boolean getVerifyAdministrator(AdministratorDTOI administratorDTOI) {
        Administrator admin = administratorRepository.getByEmail(administratorDTOI.getEmail());
        if(admin!=null)
        {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if(passwordEncoder.matches(administratorDTOI.getPassword(), admin.getPass_hash()))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public boolean addAdministrator(AdministratorDTOI administratorDTOI) {
        Administrator admin = administratorRepository.getByEmail(administratorDTOI.getEmail());
        if(admin==null)
        {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encoded_password = passwordEncoder.encode(administratorDTOI.getPassword());
            Administrator a = new Administrator(administratorDTOI.getEmail(), encoded_password);
            administratorRepository.save(a);
            return true;
        }
        else
        {
            return false;
        }
    }

    @Transactional
    public boolean deleteAdministrator(Long administratorId) {
        Optional<Administrator> optional_admin = administratorRepository.findById(administratorId);
        if(optional_admin.isPresent())
        {
            administratorRepository.deleteById(administratorId);
            return true;
        }
        else
        {
            return false;
        }
    }

    public Integer updatePasswordOfAdministrator(String email, String new_password) {
        Administrator admin = administratorRepository.getByEmail(email);
        if(admin!=null)
        {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if(!passwordEncoder.matches(new_password, admin.getPass_hash()))
            {
                String np = passwordEncoder.encode(new_password);
                admin.setPass_hash(np);
                administratorRepository.save(admin);
                return 2;
            }
            else
            {
                return 1;
            }
        }
        else
        {
            return 0;
        }
    }
}
