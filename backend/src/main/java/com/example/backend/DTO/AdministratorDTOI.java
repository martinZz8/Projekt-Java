package com.example.backend.DTO;

public class AdministratorDTOI {
    private String email;
    private String password;

    public AdministratorDTOI() {
    }

    public AdministratorDTOI(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AdministratorDTOI(AdministratorDTOI administratorDTOI) {
        this.email = administratorDTOI.email;
        this.password = administratorDTOI.password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
