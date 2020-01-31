package com.lp.service;

import com.lp.dao.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl {

	@Autowired
	private UserDaoImpl userDaoImpl;
	
	public void addUser(){
		this.userDaoImpl.saveUser();
	}
}
