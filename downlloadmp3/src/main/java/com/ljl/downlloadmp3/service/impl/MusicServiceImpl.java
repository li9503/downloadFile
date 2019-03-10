package com.ljl.downlloadmp3.service.impl;

import com.ljl.downlloadmp3.UrlUtil;
import com.ljl.downlloadmp3.service.MusicService;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


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
        String result = "";
        try {
            String urlStr = URLEncoder.encode(name, "gb2312");
            Document re = UrlUtil.getHtml("https://www.ysts8.com/Ys_so.asp?stype=1&keyword=" + urlStr);
            result = re.getElementsByClass("pingshu_ysts8").html();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result.replace("href=\"", "target=\"_blank\" href=\"searchResult.html?").replace("<br>", "\t\t\t\t");
    }

    @Override
    public Map getInfo(String url) {
        Map searchResult = new HashMap(16);
        Document re = UrlUtil.getHtml("https://www.ysts8.com" + url);
        String introduce = re.getElementsByClass("ny_txt").html();
        String result = re.getElementsByClass("ny_l").html().replace("href=\"", "href=\"/getDownloadUrl?mp3Url=").replace("</li>\n" +
                " <li>", "").split("<div class=\"ny_txt\"> ")[0];
        String about = re.getElementsByClass("xiangguan").html().replace("href=\"", "href=\"/searchResult.html?/Yshtml/");
        String title = re.title();
        String h1 = re.getElementById("i").html().split("<h1>")[1];
        System.out.println(result);
        searchResult.put("musicList", result);
        searchResult.put("about", about);
        searchResult.put("introduce", introduce);
        searchResult.put("title", title);
        searchResult.put("h1", "<h1>" + h1);
        return searchResult;
    }
}
