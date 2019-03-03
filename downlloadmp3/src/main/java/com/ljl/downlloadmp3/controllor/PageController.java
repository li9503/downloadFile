package com.ljl.downlloadmp3.controllor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author 李景磊
 * @Description
 * @Date 2019/3/3 21:20
 * @ModifiedBy：
 */
@Controller
public class PageController {
    @GetMapping("/index")
    public String toIndex(){
        return "index";
    }

    @GetMapping("/searchResult")
    public String searchResult(){
        return "searchResult";
    }
}
