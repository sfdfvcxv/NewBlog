package com.zzcedu.dao;

import com.zzcedu.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: Evan
 * @Date: 2020/8/20 17:07
 */
public interface UserDao {
    @Select("select * from cn_user where cn_user_name = #{name}")
    User findByName(String name);
    @Insert("insert into cn_user(cn_user_id,cn_user_name,cn_user_password,cn_user_nick) values(#{cn_user_id},#{cn_user_name},#{cn_user_password},#{cn_user_nick})")
    void save(User user);
}
