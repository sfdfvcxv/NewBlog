package com.zzcedu.controller;

import com.zzcedu.service.NoteService;
import com.zzcedu.util.NoteResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Evan
 * @Date: 2020/8/24 11:31
 */
@RestController
public class LoadNoteCotroller {
    @Resource
    private NoteService noteService;
    @RequestMapping("/note/load.do")
    public NoteResult execute(String noteId){
        return noteService.loadNote(noteId);
    }
}
