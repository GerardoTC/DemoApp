package com.demo.gerardo_tarazona.demoapp;

/**
 * Created by elizabethtarazona on 14/03/2017.
 */

public class UserObject {

    public String name;
    public String age;
    public String email;
    public UserObject(){

    }


    public UserObject(String age, String email, String name) {
        this.name = name;
        this.age = age;
        this.email = email;

    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }


}
