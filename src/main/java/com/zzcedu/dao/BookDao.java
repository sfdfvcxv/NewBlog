package com.zzcedu.dao;

import com.zzcedu.entity.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: Evan
 * @Date: 2020/8/22 10:40
 */
public interface BookDao {
    @Select("select * from cn_notebook where cn_user_id=#{userId}")
    List<Book> findByUserId(String userId);
    @Insert("insert into cn_notebook (cn_notebook_id,cn_user_id,cn_notebook_type_id,cn_notebook_name,cn_notebook_createtime) values(#{cn_notebook_id},#{cn_user_id},'5',#{cn_notebook_name},#{cn_notebook_createtime})")
    void save(Book book);
}
