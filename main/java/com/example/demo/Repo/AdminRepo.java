package com.example.demo.Repo;

import com.example.demo.JavaClasses.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin,Integer> {
}
