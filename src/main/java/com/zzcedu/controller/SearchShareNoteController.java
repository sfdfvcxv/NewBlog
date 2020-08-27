package com.zzcedu.controller;

import com.zzcedu.service.ShareService;
import com.zzcedu.util.NoteResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SearchShareNoteController {
    @Resource
    private ShareService shareService;
    @RequestMapping("/note/searchShare.do")
    public NoteResult execute(String keyword, int page){
        return shareService.searchShare(keyword,page);

    }
}
