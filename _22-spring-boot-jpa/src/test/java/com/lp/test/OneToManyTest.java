package com.lp.test;

import com.lp.App;
import com.lp.dao.UsersRepository;
import com.lp.pojo.Roles;
import com.lp.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;


/**
 * 一对多关联关系测试
 *
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= App.class)
public class OneToManyTest {
	
	@Autowired
	private UsersRepository usersRepository;
	
	/**
	 * 一对多关联关系的添加
	 */
	@Test
	public void testSave(){
		//创建一个用户
		Users users = new Users();
		users.setAddress("天津");
		users.setAge(32);
		users.setName("小刚");
		
		//创建一个角色
		Roles roles = new Roles();
		roles.setRolename("管理员");
		
		//关联
		roles.getUsers().add(users);
		users.setRoles(roles);
		
		//保存
		this.usersRepository.save(users);
	}
	
	/**
	 * 一对多关联关系的查询 getOne
	 */
	@Test
	public void testFind(){
		//需要在users实例上加上→@Proxy(lazy = false)
		Users users = this.usersRepository.getOne(7);
		System.out.println(users);
		Roles roles = users.getRoles();
		System.out.println(roles.getRolename());
	}

	/**
	 * 一对多关联关系的查询 findById
	 */
	@Test
	public void testFind2(){
		Optional<Users> optional = this.usersRepository.findById(7);
		System.out.println(optional.get());
		Roles roles = optional.get().getRoles();
		System.out.println(roles.getRolename());
	}




}
