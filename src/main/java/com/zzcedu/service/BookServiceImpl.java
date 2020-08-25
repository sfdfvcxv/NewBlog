package com.zzcedu.service;

import com.zzcedu.dao.BookDao;
import com.zzcedu.entity.Book;
import com.zzcedu.util.NoteResult;
import com.zzcedu.util.NoteUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: Evan
 * @Date: 2020/8/22 10:46
 */
@Service
public class BookServiceImpl implements BookService{
    @Resource
    private BookDao bookDao;
    @Override
    public NoteResult loadUserBooks(String userId) {
        NoteResult noteResult = new NoteResult();
        List<Book> books = bookDao.findByUserId(userId);
        //创建返回结果
        noteResult.setStatus(0);
        noteResult.setMsg("查询笔记成功");
        noteResult.setData(books);
        return noteResult;
    }

    @Override
    public NoteResult addBook(String userId, String name) {
        NoteResult noteResult = new NoteResult();
        Book book = new Book();
        book.setCn_notebook_id(NoteUtil.createId());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        book.setCn_notebook_createtime(timestamp);
        book.setCn_notebook_name(name);
        book.setCn_user_id(userId);
        bookDao.save(book);
        //创建返回结果集
        noteResult.setStatus(0);
        noteResult.setMsg("创建笔记成功");
        noteResult.setData(book);
        return noteResult;
    }
}
