package com.lp.service.impl;

import com.lp.dao.UsersRepository;
import com.lp.pojo.Users;
import com.lp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;


    @Override
    @Cacheable(value="users")
    public List<Users> findUsersAll() {
        return usersRepository.findAll();
    }

    @Override
    //@Cacheable:对当前查询的对象做缓存处理
    @Cacheable(value="users")
    public Users findUsersById(Integer id) {
        Optional<Users> optional = usersRepository.findById(id);
        return optional.get();
    }

    @Override
    @Cacheable(value="users", key="1")
    public Page<Users> findUsersByPage(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }

    @Override
    //@CacheEvict(value="users",allEntries=true) 清除缓存中以users缓存策略缓存的对象
    @CacheEvict(value="users",allEntries=true)
    public void saveUsers(Users users) {
        usersRepository.save(users);
    }
}
