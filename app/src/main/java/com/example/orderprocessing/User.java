package com.example.orderprocessing;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String username;
    public boolean isCustomer;

    //public String address;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, boolean isCustomer) {
        this.username = username;
        this.isCustomer = isCustomer;

    }

    public void setEmail(String email){
        this.username = email;
    }

    public String getEmail(){
        return username;
    }

    public void setIsCustomer(Boolean isCustomer){
        this.isCustomer = isCustomer;
    }

    public Boolean getIsCustomer(){
        return isCustomer;
    }

}