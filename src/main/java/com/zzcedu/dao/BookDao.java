package com.zzcedu.dao;

import com.zzcedu.entity.Book;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: Evan
 * @Date: 2020/8/22 10:40
 */
public interface BookDao {
    @Select("select * from cn_notebook where cn_user_id=#{userId}")
    List<Book> findByUserId(String userId);
}
