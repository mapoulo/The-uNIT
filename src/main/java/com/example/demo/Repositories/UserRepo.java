package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.MyUser;

public interface UserRepo extends JpaRepository<MyUser, Long> {

}
