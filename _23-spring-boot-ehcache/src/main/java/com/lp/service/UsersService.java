package com.lp.service;


import com.lp.pojo.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsersService {
	
	List<Users> findUsersAll();
	Users findUsersById(Integer id);
	Page<Users> findUsersByPage(Pageable pageable);
	void saveUsers(Users users);
}
