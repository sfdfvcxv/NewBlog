package com.zzcedu.service;

import com.zzcedu.dao.UserDao;
import com.zzcedu.entity.User;
import com.zzcedu.util.NoteResult;
import com.zzcedu.util.NoteUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Evan
 * @Date: 2020/8/21 10:11
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public NoteResult checkLogin(String name, String password) {
        NoteResult noteResult = new NoteResult();
        //判断用户名存在
        User user = userDao.findByName(name);
        if (user == null) {
            noteResult.setStatus(1);
            noteResult.setMsg("用户名不存在");
            return noteResult;
        }
        //判断密码
        try {
            String md5_pwd = NoteUtil.md5(password);
            if (!user.getCn_user_password().equals(md5_pwd)) {
                noteResult.setStatus(2);
                noteResult.setMsg("密码错误");
                return noteResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //登录成功
        noteResult.setStatus(0);
        noteResult.setMsg("登录成功");
        //把密码信息屏蔽掉
        user.setCn_user_password("");
        noteResult.setData(user);
        return noteResult;
    }

    @Override
    public NoteResult addUser(String name, String nick, String password) {
        NoteResult noteResult = new NoteResult();
        try {
            //判断用户名是否存在
            User user = userDao.findByName(name);
            if (user != null) {
                noteResult.setStatus(1);
                noteResult.setMsg("用户名已被占用");
                return noteResult;
            }
            //执行注册操作
            User user1 = new User();
            user1.setCn_user_id(NoteUtil.createId());
            user1.setCn_user_password(NoteUtil.md5(password));
            user1.setCn_user_name(name);
            user1.setCn_user_nick(nick);
            userDao.save(user1);
            //创建返回值
            noteResult.setMsg("注册成功");
            noteResult.setStatus(0);
            return noteResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
