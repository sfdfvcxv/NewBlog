package com.zzcedu.dao;

import com.zzcedu.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: Evan
 * @Date: 2020/8/20 17:07
 */
public interface UserDao {
    @Select("select * from cn_user")
    List<User> findAll();
    @Select("select * from cn_user where cn_user_name = #{name}")
    public User findByName(String name);
}
