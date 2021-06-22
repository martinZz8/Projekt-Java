package com.example.backend.model;

import javax.persistence.*;

@Entity(name = "administrator")
@Table(name = "administrator", uniqueConstraints ={
        @UniqueConstraint(name = "administrator_email_unique", columnNames = "email")
})
public class Administrator {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "email", nullable = false, length = 40)
    private String email;
    @Column(name = "pass_hash", nullable = false, length = 60)
    private String pass_hash;

    public Administrator() {
    }

    public Administrator(Long id, String email, String pass_hash) {
        this.id = id;
        this.email = email;
        this.pass_hash = pass_hash;
    }

    public Administrator(String email, String pass_hash) {
        this.email = email;
        this.pass_hash = pass_hash;
    }

    public Administrator(Administrator administrator) {
        this.id = administrator.id;
        this.email = administrator.email;
        this.pass_hash = administrator.pass_hash;
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

    public String getPass_hash() {
        return pass_hash;
    }

    public void setPass_hash(String pass_hash) {
        this.pass_hash = pass_hash;
    }
}
