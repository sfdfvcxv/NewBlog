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
}
