package com.example.demo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.MyUser;
import com.example.demo.Repositories.UserRepo;


@Service
public class UserService {

	@Autowired
	private UserRepo repo;
	
	public MyUser saveUser(MyUser user) {
		return repo.save(user);
	}
	
	
	public List<MyUser> getAllUser(){
		return repo.findAll().stream().toList();
	}
}
