package com.ljl.downlloadmp3.controllor;

import com.ljl.downlloadmp3.UrlUtil;
import com.ljl.downlloadmp3.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 李景磊
 * @Description
 * @Date 2019/3/2 22:56
 * @ModifiedBy：
 */
@RestController
public class UrlControllor {

    @Autowired
    MusicService musicService;
    @GetMapping("/getDownloadUrl")
    public String getDownloadUrl(String mp3Url){
     String downloadUrl=musicService.getDownLoadUrl(mp3Url);
        System.out.println(downloadUrl);
     return downloadUrl;
    }
}
