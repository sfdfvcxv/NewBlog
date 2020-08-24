package com.zzcedu.service;

import com.zzcedu.dao.NoteDao;
import com.zzcedu.entity.Note;
import com.zzcedu.util.NoteResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: Evan
 * @Date: 2020/8/22 16:39
 */
@Service
public class NoteServiceImpl implements NoteService{
    @Resource
    private NoteDao noteDao;
    @Override
    public NoteResult loadNotes(String bookId) {
        NoteResult noteResult = new NoteResult();
        List<Map> notes = noteDao.findByBookId(bookId);
        //创建返回结果
        noteResult.setStatus(0);
        noteResult.setMsg("查询完毕");
        noteResult.setData(notes);
        return noteResult;
    }

    @Override
    public NoteResult loadNote(String noteId) {
        NoteResult result = new NoteResult();
        //根据笔记id查询笔记信息
        Note note = noteDao.findById(noteId);
        //创建返回结果
        result.setStatus(0);
        result.setMsg("查询完毕");
        result.setData(note);
        return result;
    }

    @Override
    public NoteResult updateNote(String noteId, String title, String body) {
        NoteResult noteResult = new NoteResult();
        Note note = new Note();
        note.setCn_note_id(noteId);
        note.setCn_note_title(title);
        note.setCn_note_body(body);
        //笔记修改时间
        note.setCn_note_last_modify_time(System.currentTimeMillis());
        int i = noteDao.updateNote(note);
        if( i == 1){//成功
            noteResult.setStatus(0);
            noteResult.setMsg("保存笔记成功");
        }else { //失败
            noteResult.setStatus(1);
            noteResult.setMsg("保存笔记失败");
        }
        return noteResult;
    }
}
