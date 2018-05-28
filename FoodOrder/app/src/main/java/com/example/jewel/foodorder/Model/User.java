package com.example.jewel.foodorder.Model;

/**
 * Created by fiber on 12-Dec-17.
 */

public class User {

    private String Name;
    private String Password;
    private String Phone;

    public User(){}

    public User(String name, String password, String phone) {
        Name = name;
        Password = password;
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPhone() {
        return Phone;
    }
}
