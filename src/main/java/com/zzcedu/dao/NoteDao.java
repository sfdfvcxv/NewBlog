package com.zzcedu.dao;

import com.zzcedu.entity.Note;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @Author: Evan
 * @Date: 2020/8/22 16:31
 */
public interface NoteDao {
    @Select("select cn_note_id,cn_note_title,cn_note_type_id from cn_note where cn_notebook_id=#{bookId} and cn_note_status_id='1' ")
    List<Map> findByBookId(String bookId);
    @Select("select cn_note_id,cn_notebook_id,cn_user_id,cn_note_status_id,cn_note_type_id,cn_note_title,cn_note_body,cn_note_create_time,cn_note_last_modify_time from cn_note where cn_note_id =#{noteId}")
    Note findById(String noteId);
    @Update("update cn_note set cn_note_title=#{cn_note_title},cn_note_body=#{cn_note_body},cn_note_last_modify_time =#{cn_note_last_modify_time} where cn_note_id = #{cn_note_id}")
    int updateNote(Note note);
}
