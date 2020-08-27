package com.zzcedu.controller;

import com.zzcedu.entity.Note;
import com.zzcedu.service.ShareService;
import com.zzcedu.util.NoteResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ShareNoteController {
    @Resource
    private ShareService shareService;
    @RequestMapping("/note/share.do")
    public NoteResult execute(String noteId){
        return shareService.shareNote(noteId);
    }
    @RequestMapping("/share/ShareBody.do")
    public NoteResult findShareBody(String shareId){
        return shareService.findShareBody(shareId);
    }
}
