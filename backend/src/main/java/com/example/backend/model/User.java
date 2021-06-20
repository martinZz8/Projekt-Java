package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "user")
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(name = "user_email_unique", columnNames = "email")
})
//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "email", nullable = false, length = 40)
    private String email;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "dob")
    private LocalDate dob;
    @Transient
    private Integer age;
    @Column(name = "pass_hash", nullable = false, length = 60)
    private String pass_hash;
    @Column(name = "blocked", nullable = false, length = 1)
    private Integer blocked;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonIgnore
    List<ShoppingList> shoppingList;

    public User() {
    }

    public User(Long id, String email, String first_name, String last_name, LocalDate dob, String pass_hash, Integer blocked) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        this.pass_hash = pass_hash;
        this.blocked = blocked;
        this.shoppingList = new ArrayList<>();
    }

    public User(String email, String first_name, String last_name, LocalDate dob, String pass_hash, Integer blocked) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        this.pass_hash = pass_hash;
        this.blocked = blocked;
        this.shoppingList = new ArrayList<>();
    }

    public User(User copy_user) {
        this.id = copy_user.id;
        this.email = copy_user.email;
        this.first_name = copy_user.first_name;
        this.last_name = copy_user.last_name;
        this.dob = copy_user.dob;
        this.age = copy_user.age;
        this.pass_hash = copy_user.pass_hash;
        this.blocked = copy_user.blocked;
        this.shoppingList=copy_user.shoppingList;
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

    public String getPass_hash() {
        return pass_hash;
    }

    public void setPass_hash(String pass_hash) {
        this.pass_hash = pass_hash;
    }

    public Integer getBlocked() {
        return blocked;
    }

    public void setBlocked(Integer blocked) {
        this.blocked = blocked;
    }

    public List<ShoppingList> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(List<ShoppingList> shoppingList) {
        this.shoppingList = shoppingList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", dob=" + dob +
                ", pass_hash='" + pass_hash + '\'' +
                ", blocked=" + blocked +
                '}';
    }
}
