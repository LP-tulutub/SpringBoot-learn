package com.lp.controller;

import com.lp.pojo.User;
import com.lp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Resource
    private UserService userServiceImpl;

    /**
     * 插入user数据
     * @param user
     * @return
     */
    @RequestMapping("/addUser")
    public String insUser(User user){
        int index = userServiceImpl.insUser(user);
        if (index==1){
            return "ok";
        }
        return "error";
    }

    /**
     * 查询全部user
     * @param model
     * @return
     */
    @RequestMapping("/findUserAll")
    public String selAll(Model model){
        List<User> userList = userServiceImpl.selAll();
        model.addAttribute("list", userList);
        return "showUsers";
    }

    /**
     * 用户回显
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/findUserById")
    public String findUserById(Model model, int id){
        User user = userServiceImpl.selById(id);
        model.addAttribute("user", user);
        return "updateUser";
    }

    /**
     * 用户修改
     * @param user
     * @return
     */
    @RequestMapping("/editUser")
    public String editUser(User user){
        int updI = userServiceImpl.updUser(user);
        if (updI==1){
            return "redirect:/users/findUserAll";
        }
        return "error";
    }

    /**
     * 按id删除用户
     * @param id
     * @return
     */
    @RequestMapping("/delUser")
    public String delUser(int id){
        int delI = userServiceImpl.delUser(id);
        if (delI==1){
            return "redirect:/users/findUserAll";
        }
        return "error";
    }
}
