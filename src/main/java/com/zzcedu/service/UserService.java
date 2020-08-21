package com.zzcedu.service;

import com.zzcedu.util.NoteResult;

/**
 * @Author: Evan
 * @Date: 2020/8/21 10:09
 */
public interface UserService {
    NoteResult checkLogin(String name,String password);
    NoteResult addUser(String name,String nick,String password);
}
