package com.jxnu.controller;


import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.jxnu.entity.User;
import com.jxnu.service.IUserService;
import com.jxnu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author h.h.Huang
 * @since 2022-12-13
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService iUserService;
    @GetMapping("/gourmetdaren")
    public Result getGourmetDaren(){
        List<User> gourmetDaren = iUserService.getGourmetDaren();
        if(!ObjectUtils.isEmpty(gourmetDaren)){
            return Result.ok(gourmetDaren).message("获取美食达人信息成功");
        }
        return Result.error().message("获取美食达人信息失败");
    }
    @PostMapping("/getuser")
    public Result getUser(@RequestBody User user){
        User username = iUserService.findUserById(user.getId());
        if(!ObjectUtils.isEmpty(username)){
            return Result.ok(username).message("获取用户信息成功");
        }
        return Result.error().message("获取用户信息失败");

    }
    @PostMapping("/getuserlist")
    public Result getUser(@RequestBody List<User> userList){
        List<User> usernameList = iUserService.findUserListById(userList);

        if(!ObjectUtils.isEmpty(usernameList)){
            return Result.ok(usernameList).message("获取用户信息成功");
        }
        return Result.error().message("获取用户信息失败");

    }
    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpSession session) {
        boolean exists = iUserService.checkLogin(user);
        if (exists) {
            User user1 = iUserService.findUserList(user);
            return Result.ok(user1).message("登录成功！");
        }
        return Result.error().message("用户名或密码错误");
    }



}
