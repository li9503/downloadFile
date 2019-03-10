package com.ljl.downlloadmp3.controllor;


import com.ljl.downlloadmp3.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

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



    @GetMapping("/search")
    public String search(String name) {
        System.out.println(name);
        String result = musicService.searchByName(name);
//        System.out.println(result);
        return result;
    }

    @GetMapping("/getList")
    public Map get(String url) {
//        System.out.println(url);
       Map mp3Url= musicService.getInfo(url);
//        System.out.println(mp3Url);
        return mp3Url;
    }

}
