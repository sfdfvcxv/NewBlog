package com.zzcedu.controller;

import com.zzcedu.service.UserService;
import com.zzcedu.util.NoteResult;
import com.zzcedu.util.NoteUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Evan
 * @Date: 2020/8/21 10:25
 */
@RestController
public class UserLoginController {
    @Resource
    private UserService userService;
    @RequestMapping("/user/login.do")
    public NoteResult execute(String name, String password){
        return userService.checkLogin(name,password);
    }
}
