package com.zzcedu.controller;

import com.zzcedu.service.NoteService;
import com.zzcedu.util.NoteResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Evan
 * @Date: 2020/8/25 17:23
 */
@RestController
public class AddNoteController {
    @Resource
    private NoteService noteService;
    @RequestMapping("/note/add.do")
    public NoteResult execute(String noteTitle,String bookId,String userId){
        return noteService.addNote(noteTitle,bookId,userId);
    }
}
