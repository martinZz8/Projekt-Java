package com.example.backend.DTO;

import com.example.backend.model.Administrator;

public class AdministratorDTOO {
    private Long id;
    private String email;

    public AdministratorDTOO() {
    }

    public AdministratorDTOO(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public AdministratorDTOO(AdministratorDTOO administratorDTOO) {
        this.id = administratorDTOO.id;
        this.email = administratorDTOO.email;
    }

    public AdministratorDTOO(Administrator administrator) {
        this.id = administrator.getId();
        this.email = administrator.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
