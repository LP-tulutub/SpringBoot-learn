package com.lp.dao;


import com.lp.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 
 *JpaSpecificationExecutor
 *
 */
public interface UsersRepositorySpecification extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users> {

}
