package org.qingqiao.vhr.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.qingqiao.vhr.utils.ResponseBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    @GetMapping("/login")
    public String tologin() throws JsonProcessingException {
        ResponseBean error = ResponseBean.error("请先登录");
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(error);
        return s;
    }
    @GetMapping("/employee/basic/hello")
    public String hello1(){
        return "hesssssssssssssssssssss";
    }
    @GetMapping("/employee/advanced/hello")
    public String hello2(){
        return "asddddddsdasdasdsdl;asl;dk";
    }
}
