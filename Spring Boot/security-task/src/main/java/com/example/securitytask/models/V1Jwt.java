package com.example.securitytask.models;


import javax.persistence.*;

@Entity
@Table(name="v1jwt")
public class V1Jwt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String jwt;
    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)

    private User user;
    private String jwtName;

    public String getJwtName() {
        return jwtName;
    }

    public void setJwtName(String jwtName) {
        this.jwtName = jwtName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
