package com.lp.service.impl;

import com.lp.mapper.UserMapper;
import com.lp.pojo.User;
import com.lp.pojo.UserExample;
import com.lp.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * user表插入数据
     * @param user
     * @return
     */
    @Override
    public int insUser(User user) {
        return userMapper.insertSelective(user);
    }

    /**
     * 查询全部
      * @return
     */
    @Override
    public List<User> selAll() {
        return userMapper.selectByExample(new UserExample());
    }

    /**
     * 按id查询
     * @param id
     * @return
     */
    @Override
    public User selById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @Override
    public int updUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @Override
    public int delUser(int id) {
        return userMapper.deleteByPrimaryKey(id);
    }
}
