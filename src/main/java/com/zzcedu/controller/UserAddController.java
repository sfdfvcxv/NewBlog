package com.zzcedu.controller;

import com.zzcedu.service.UserService;
import com.zzcedu.util.NoteResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Evan
 * @Date: 2020/8/21 16:13
 */
@RestController
public class UserAddController {
    @Resource
    private UserService userService;
    @RequestMapping("/user/add.do")
    public NoteResult execute(String name,String nick,String password){
        return userService.addUser(name,nick,password);
    }
}
