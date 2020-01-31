package com.lp.dao;


import com.lp.pojo.Users;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 
 *PagingAndSortingRepository接口
 *
 */
public interface UsersRepositoryPagingAndSorting extends PagingAndSortingRepository<Users,Integer> {

}
