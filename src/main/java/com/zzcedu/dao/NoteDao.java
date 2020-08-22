package com.zzcedu.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author: Evan
 * @Date: 2020/8/22 16:31
 */
public interface NoteDao {
    @Select("select cn_note_id,cn_note_title,cn_note_type_id from cn_note where cn_notebook_id=#{bookId} and cn_note_status_id='1' ")
    List<Map> findByBookId(String bookId);
}
