package com.denarisolutions.denariapp_springboot.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String street;
    @Column
    private String appt_number;
    @Column
    private String city;
    @Column
    private String state;
    @Column
    private String postal_code;

    @ManyToOne
    @JoinColumn(name = "propertymanagement_id")
    private PropertyManagement propertymanagement;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_addresses",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "address_id")}
    )
    private Set<User> users;

    public Address(){
    }

    public Address(long id, String street, String appt_number, String city, String state, String postal_code){
        this.id = id;
        this.street = street;
        this.appt_number = appt_number;
        this.city = city;
        this.state = state;
        this.postal_code = postal_code;
    }
    public Address(String street, String appt_number, String city, String state, String postal_code){
        this.street = street;
        this.appt_number = appt_number;
        this.city = city;
        this.state = state;
        this.postal_code = postal_code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAppt_number() {
        return appt_number;
    }

    public void setAppt_number(String appt_number) {
        this.appt_number = appt_number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<User> getUsers() {
        return users;
    }
}
