package com.sc.aqjl.business;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/hello/{name}")
    public String toIndex(@PathVariable("name") String name, Model model){
        model.addAttribute("name", name);
        return "index";
    }
}
