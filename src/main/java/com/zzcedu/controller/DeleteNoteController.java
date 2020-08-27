package com.zzcedu.controller;

import com.zzcedu.service.NoteService;
import com.zzcedu.util.NoteResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Evan
 * @Date: 2020/8/26 11:53
 */
@RestController
public class DeleteNoteController {
    @Resource
    private NoteService noteService;
    @RequestMapping("/note/delete.do")
    public NoteResult execute(String noteId){
        return noteService.deleteNote(noteId);
    }
}
