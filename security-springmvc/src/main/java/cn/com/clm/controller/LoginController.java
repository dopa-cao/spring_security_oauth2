package cn.com.clm.controller;

import cn.com.clm.dto.AuthenticationRequest;
import cn.com.clm.dto.UserDto;
import cn.com.clm.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/login", produces = {"text/plain;charset=utf-8"})
    public String login(AuthenticationRequest authenticationRequest, HttpSession session) {
        UserDto userDto = authenticationService.authentication(authenticationRequest);
        session.setAttribute(UserDto.SESSION_USER_KEY, userDto);
        return userDto.getFullname() + " login success !";
    }

    @GetMapping(value = "/logout", produces = {"text/plain;charset=utf-8"})
    public String logout(HttpSession session) {
        session.invalidate();
        return "logout out success !";
    }

    @GetMapping(value = "/r/r1", produces = {"text/plain;charset=utf-8"})
    public String r1(HttpSession session) {
        String fullname = null;
        Object object = session.getAttribute(UserDto.SESSION_USER_KEY);
        if (object != null) {
            fullname = ((UserDto) object).getFullname();
        } else {
            fullname = "unKnown";
        }
        return fullname + " go r1 !";
    }

    @GetMapping(value = "/r/r2", produces = {"text/plain;charset=utf-8"})
    public String r2(HttpSession session) {
        String fullname = null;
        Object object = session.getAttribute(UserDto.SESSION_USER_KEY);
        if (object != null) {
            fullname = ((UserDto) object).getFullname();
        } else {
            fullname = "unKnown";
        }
        return fullname + " go r2 !";
    }


}
