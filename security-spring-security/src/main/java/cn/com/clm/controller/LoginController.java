package cn.com.clm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @RequestMapping(value = "/login-success", produces = {"text/plain;charset=utf-8"})
    public String loginSuccess() {
        return "login success !";
    }

    @GetMapping(value = "/r/r1", produces = {"text/plain;charset=utf-8"})
    public String r1() {
        return  " go r1 !";
    }

    @GetMapping(value = "/r/r2", produces = {"text/plain;charset=utf-8"})
    public String r2() {
        return  " go r2 !";
    }

}
