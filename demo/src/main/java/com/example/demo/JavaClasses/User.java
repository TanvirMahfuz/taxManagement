package com.example.demo.JavaClasses;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
@Component
public class User {
    int id;
    String name;
    Lawyer lawyer;
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    String input;

    void setLawyer(Lawyer lawyer){
        this.lawyer=lawyer;
        lawyer.setUsers(this);
    }


    void setInput(String s){
        this.input=s;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}