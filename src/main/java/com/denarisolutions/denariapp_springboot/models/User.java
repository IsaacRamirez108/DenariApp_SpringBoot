package com.denarisolutions.denariapp_springboot.models;


public class User {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String SSN;
    private String DOB;
    private String phoneNumber;

    public User(long id, String firstName, String lastName, String email, String password, String SSN, String DOB, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.SSN = SSN;
        this.DOB = DOB;
        this.phoneNumber = phoneNumber;
    }



}
