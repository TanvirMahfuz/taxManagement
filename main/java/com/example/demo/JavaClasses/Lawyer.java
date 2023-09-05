package com.example.demo.JavaClasses;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@Entity
public class Lawyer {

    @Id
    int lawyerID;

    String LawyerName;

    int salary;
    @OneToMany(mappedBy = "lawyer", cascade = CascadeType.ALL)
    List<User> users = new ArrayList<>();
    @ManyToOne()
    @JoinColumn(name = "adminID")
    Admin admin;
    void addUsers(User u){
        users.add(u);
    }

    public Lawyer emptyLawyer(){
        LawyerName="No Lawyer Selected";
        return this;
    }
    private String lawyerEmail;

    public String getEmail() {
        return lawyerEmail;
    }
    public void setEmail(String email) {
        this.lawyerEmail = email;
    }

    private String Lpassword;

    public String getLpassword() {
        return Lpassword;
    }
    public void setLpassword(String lpassword) {
        Lpassword = lpassword;
    }

    public int getLawyerID() {
        return lawyerID;
    }

    public void setLawyerID(int lawyerID) {
        this.lawyerID = lawyerID;
    }

    public String getLawyerName() {
        return LawyerName;
    }
    public void setLawyerName(String name) {
        this.LawyerName = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }


    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
    @Override
    public String toString() {
        return "Lawyer{" +
                ", name='" + LawyerName + '\'' +
                ", salary=" + salary +
                '}';
    }


}