package com.zzcedu.controller;

import com.zzcedu.service.NoteService;
import com.zzcedu.util.NoteResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Evan
 * @Date: 2020/8/22 16:42
 */
@RestController
public class LoadNotesController {
    @Resource
    private NoteService noteService;
    @RequestMapping("/note/loadnotes.do")
    public NoteResult execute(String bookId){
        return noteService.loadNotes(bookId);
    }
}
