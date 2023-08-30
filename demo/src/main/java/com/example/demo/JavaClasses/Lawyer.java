package com.example.demo.JavaClasses;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class Lawyer {
    int lawyerID;
    String name;
    int salary;

    String passworod;

    String Gender;
    Qualifications qualifications;
    public int getLawyerID() {
        return lawyerID;
    }

    public void setLawyerID(int lawyerID) {
        this.lawyerID = lawyerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    ArrayList<User> users = new ArrayList<>();
    void setUsers(User u){
        users.add(u);
    }
    @Override
    public String toString() {
        return "Lawyer{" +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }


}