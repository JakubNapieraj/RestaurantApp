package com.dto;

public class UserDTO {

    private Long id;

    public Long getId() {
        return id;
    }

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String city;
    private String steetNumber;
    private String postCode;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSteetNumber() {
        return steetNumber;
    }

    public void setSteetNumber(String steetNumber) {
        this.steetNumber = steetNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
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
