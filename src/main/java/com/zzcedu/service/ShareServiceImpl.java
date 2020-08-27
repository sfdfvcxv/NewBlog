package com.zzcedu.service;

import com.zzcedu.dao.NoteDao;
import com.zzcedu.dao.ShareDao;
import com.zzcedu.entity.Note;
import com.zzcedu.entity.Share;
import com.zzcedu.util.NoteResult;
import com.zzcedu.util.NoteUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class ShareServiceImpl implements ShareService{
    @Resource
    private ShareDao shareDao;
    @Resource
    private NoteDao noteDao;
    @Override
    public NoteResult shareNote(String noteId) {
        NoteResult noteResult=new NoteResult();
        Note byId = noteDao.findById(noteId);
        if ("2".equals(byId.getCn_note_type_id())){
            noteResult.setStatus(1);
            noteResult.setMsg("笔记分享过了");
            return noteResult;
        }
        Note note=new Note();
        note.setCn_note_id(noteId);
        note.setCn_note_type_id("2");
        noteDao.updateNote(note);
        Share share=new Share();
        share.setCn_share_id(NoteUtil.createId());
        share.setCn_note_id(noteId);
        share.setCn_share_body(byId.getCn_note_body());
        share.setCn_share_title(byId.getCn_note_title());
        shareDao.save(share);
        noteResult.setStatus(0);
        noteResult.setMsg("笔记分享成功");
        return noteResult;
    }

    @Override
    public NoteResult searchShare(String keyword, int page) {
        NoteResult noteResult=new NoteResult();
        if (page<1){
            page=1;
        }
        if ("".equals(keyword)){
            String key="%";
        }
        if (keyword==null){
            noteResult.setStatus(1);
            noteResult.setMsg("查询失败");
        }
        String key="%"+keyword+"%";
        int page1=(page-1)*5;
        List<Share> likeTitle = shareDao.findLikeTitle(key, page1);
        noteResult.setStatus(0);
        noteResult.setMsg("查询成功");
        noteResult.setData(likeTitle);
        return noteResult;
    }

    @Override
    public NoteResult findShareBody(String shareId) {
        NoteResult noteResult=new NoteResult();
        Share shareBody = shareDao.findShareBody(shareId);
        noteResult.setStatus(0);
        noteResult.setMsg("搜索笔记查询详情成功");
        noteResult.setData(shareBody);
        return noteResult;
    }
}
