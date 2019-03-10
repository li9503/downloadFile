package com.ljl.downlloadmp3.controllor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author 李景磊
 * @Description
 * @Date 2019/3/3 21:20
 * @ModifiedBy：
 */
@Controller
public class PageController {
    @GetMapping("/")
    public String toIndex(){
        return "index";
    }

    @GetMapping("/searchResult")
    public ModelAndView searchResult(String title){
        ModelAndView modelAndView=new ModelAndView("searchResult");
        System.out.println(title);
        modelAndView.addObject("result",title);
        return modelAndView;
    }
}
