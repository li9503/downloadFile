package com.ljl.downlloadmp3.service.impl;

import com.ljl.downlloadmp3.UrlUtil;
import com.ljl.downlloadmp3.service.MusicService;
import org.springframework.stereotype.Service;


/**
 * @Author 李景磊
 * @Description
 * @Date 2019/3/3 19:24
 * @ModifiedBy：
 */
@Service("musicService")
public class MusicServiceImpl implements MusicService {
    @Override
    public String getDownLoadUrl(String mp3Url) {
        return UrlUtil.getDownloadUrl(mp3Url);
    }
}
