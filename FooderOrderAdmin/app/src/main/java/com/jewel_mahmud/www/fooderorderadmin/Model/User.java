package com.jewel_mahmud.www.fooderorderadmin.Model;

/**
 * Created by Jewel on 2/22/2018.
 */

public class User {

    private String Name;
    private String Phone;
    private String Password;
    private String isStuff;

    public User(){

    }

    public User(String name, String phone, String password, String isStuff) {
        Name = name;
        Phone = phone;
        Password = password;
        this.isStuff = isStuff;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {

        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getIsStuff() {
        return isStuff;
    }

    public void setIsStuff(String isStuff) {
        this.isStuff = isStuff;
    }
}
