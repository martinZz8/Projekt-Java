package com.example.backend.DTO;

import com.example.backend.model.User;

import java.time.LocalDate;
import java.time.Period;

public class UserDTOO {
    private Long id;
    private String email;
    private String first_name;
    private String last_name;
    private LocalDate dob;
    private Integer age;
    private Integer blocked;

    public UserDTOO() {
    }

    public UserDTOO(Long id, String email, String first_name, String last_name, LocalDate dob, Integer blocked) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        this.blocked = blocked;
    }

    public UserDTOO(UserDTOO new_user) {
        this.id = new_user.id;
        this.email = new_user.email;
        this.first_name = new_user.first_name;
        this.last_name = new_user.last_name;
        this.dob = new_user.dob;
        this.age = new_user.age;
        this.blocked = new_user.blocked;
    }

    public UserDTOO(User new_user) {
        this.id = new_user.getId();
        this.email = new_user.getEmail();
        this.first_name = new_user.getFirst_name();
        this.last_name = new_user.getLast_name();
        this.dob = new_user.getDob();
        this.age = new_user.getAge();
        this.blocked = new_user.getBlocked();
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

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getBlocked() {
        return blocked;
    }

    public void setBlocked(Integer blocked) {
        this.blocked = blocked;
    }
}
