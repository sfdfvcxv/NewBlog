package com.zzcedu.service;

import com.zzcedu.entity.Share;
import com.zzcedu.util.NoteResult;

public interface ShareService {
    NoteResult shareNote(String noteId);
    NoteResult searchShare(String keyword, int page);
    NoteResult findShareBody(String shareId);
}
