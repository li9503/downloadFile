package com.ljl.downlloadmp3.controllor;


import com.ljl.downlloadmp3.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 李景磊
 * @Description
 * @Date 2019/3/2 22:56
 * @ModifiedBy：
 */
@RestController
public class UrlController {

    @Autowired
    MusicService musicService;

    @GetMapping("/getDownloadUrl")
    public String getDownloadUrl(String mp3Url) {
        System.out.println(mp3Url);
        String downloadUrl = musicService.getDownLoadUrl("http://www.ysts8.com"+mp3Url);
        System.out.println(downloadUrl);
        return downloadUrl;
    }



    @PostMapping("/search")
    public String search(String name) {
        System.out.println(name);
        String result = musicService.searchByName(name);
        System.out.println(result);
        return result;
    }

    @GetMapping("/test")
    public String get(String url) {
        System.out.println(url);
       String mp3Url= musicService.getInfo(url);
        System.out.println(mp3Url);
        return mp3Url;
    }

}
