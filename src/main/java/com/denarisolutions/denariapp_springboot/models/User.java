package com.denarisolutions.denariapp_springboot.models;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;

import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String firstName;
    @Column
    private String middleName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String userName;
    @Column
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<RentalData> RentalDatalist;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<PropertyManagement> PropertyManagements;
    @ManyToMany(mappedBy = "users")
    private List<PropertyManagement> PropertyManagements;

    @ManyToMany(mappedBy = "users")
    private List<Address> Address;
    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        firstName = copy.firstName;
        middleName = copy.middleName;
        lastName = copy.lastName;
        userName = copy.userName;
        email = copy.email;
        password = copy.password;
    }
    public User(long id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
    public User() {}
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.firstName = middleName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return userName;
    }
    public void setUsername(String username) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
