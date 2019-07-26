package com.chenxianyong.clntroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: ChenXianyong
 * @description: Controller层
 * @date: 2019/7/25 14:20
 */
@Controller
public class TestController {

    @ResponseBody
    @RequestMapping("/test")
    public String test() {

        System.out.println("test1111");
        return "Test";
    }
}
