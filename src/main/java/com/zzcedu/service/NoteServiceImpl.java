package com.zzcedu.service;

import com.zzcedu.dao.NoteDao;
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
}
