package com.lp.service;

import com.lp.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 插入数据
     * @param user
     * @return
     */
    int insUser(User user);

    /**
     * 查询全部
     * @return
     */
    List<User> selAll();

    /**
     * 按id查询
     * @param id
     * @return
     */
    User selById(int id);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    int updUser(User user);

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    int delUser(int id);
}
