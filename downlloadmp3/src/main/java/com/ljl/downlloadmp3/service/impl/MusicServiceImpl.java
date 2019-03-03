package com.ljl.downlloadmp3.service.impl;

import com.ljl.downlloadmp3.UrlUtil;
import com.ljl.downlloadmp3.service.MusicService;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


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

    @Override
    public String searchByName(String name) {
        String result="";
        try {
            String urlStr = URLEncoder.encode(name, "gb2312");
            Document re= UrlUtil.getHtml("https://www.ysts8.com/Ys_so.asp?stype=1&keyword="+urlStr);
            result=re.getElementsByClass("pingshu_ysts8").html();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result.replace("href=\"","href=\"/test?url=");
    }

    @Override
    public String getInfo(String url) {


            Document re= UrlUtil.getHtml("https://www.ysts8.com"+url);
        String    result=re.getElementsByClass("ny_l").html();

        return result.replace("href=\"","href=\"/getDownloadUrl?mp3Url=");
    }
}
