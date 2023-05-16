package com.denarisolutions.denariapp_springboot.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="property_manager")
public class PropertyManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String individual_company;
    @Column
    private String name;
    @Column
    private String contact;
    @Column
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "propertymanagement")
    private List<Address> Addresses;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "propertymanagement")
    private List<RentalData> RentalDatalist;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_property-manager",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "property-manager_id")}
    )
    private List<User> users;

    public PropertyManagement() {

    }
    public PropertyManagement(long id, String individual_company, String name, String contact, String email){
        this.id = id;
        this.individual_company = individual_company;
        this.name = name;
        this.contact = contact;
        this.email = email;
    }

    public PropertyManagement(String individual_company, String name, String contact, String email) {
        this.individual_company = individual_company;
        this.name = name;
        this.contact = contact;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIndividual_company() {
        return individual_company;
    }

    public void setIndividual_company(String individual_company) {
        this.individual_company = individual_company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
