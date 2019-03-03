package com.ljl.downlloadmp3.service;


/**
 * @Author 李景磊
 * @Description
 * @Date 2019/3/3 19:20
 * @ModifiedBy：
 */
public interface MusicService {
    String getDownLoadUrl(String mp3Url);

    String searchByName(String name);

    String getInfo(String url);
}
