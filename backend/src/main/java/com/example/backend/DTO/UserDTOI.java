package com.example.backend.DTO;

import java.time.LocalDate;

public class UserDTOI {
    private String email;
    private String first_name;
    private String last_name;
    private LocalDate dob;
    private String password;
    private Integer blocked;

    public UserDTOI() {
    }

    public UserDTOI(String email, String first_name, String last_name, LocalDate dob, String password, Integer blocked) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        this.password = password;
        this.blocked = blocked;
    }

    public UserDTOI(UserDTOI new_user) {
        this.email = new_user.email;
        this.first_name = new_user.first_name;
        this.last_name = new_user.last_name;
        this.dob = new_user.dob;
        this.password = new_user.password;
        this.blocked = new_user.blocked;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getBlocked() {
        return blocked;
    }

    public void setBlocked(Integer blocked) {
        this.blocked = blocked;
    }
}
