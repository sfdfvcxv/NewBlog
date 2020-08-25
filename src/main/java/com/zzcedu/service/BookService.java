package com.zzcedu.service;

import com.zzcedu.util.NoteResult;

/**
 * @Author: Evan
 * @Date: 2020/8/22 10:44
 */
public interface BookService {
    NoteResult loadUserBooks(String userId);
    NoteResult addBook(String userId,String name);
}
