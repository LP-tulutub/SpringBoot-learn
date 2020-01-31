package com.lp.dao;


import com.lp.pojo.Users;
import org.springframework.data.repository.CrudRepository;

/**
 * CrudRepository接口
 *
 *
 */
public interface UsersRepositoryCrudRepository extends CrudRepository<Users, Integer> {

}
