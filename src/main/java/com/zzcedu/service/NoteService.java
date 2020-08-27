package com.zzcedu.service;

import com.zzcedu.util.NoteResult;

/**
 * @Author: Evan
 * @Date: 2020/8/22 16:38
 */
public interface NoteService {
    NoteResult loadNotes(String bookId);
    NoteResult loadNote(String noteId);
    NoteResult updateNote(String noteId,String title,String body);
    NoteResult addNote(String noteTitle,String bookId,String userId);
    NoteResult deleteNote(String noteId);

    /**
     * 移动笔记
     * @param noteId 笔记本id
     * @param bookId 笔记id
     * @return
     */
    NoteResult moveNote(String noteId,String bookId);

}
