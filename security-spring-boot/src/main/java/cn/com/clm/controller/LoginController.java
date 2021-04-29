package cn.com.clm.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;

@RestController
public class LoginController {

    @RequestMapping(value = "/login-success", produces = {"text/plain;charset=utf-8"})
    public String loginSuccess() {
        String username = getUsername();
        return username + " login success !";
    }

    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    @GetMapping(value = "/r/r1", produces = {"text/plain;charset=utf-8"})
//    @PreAuthorize("hasAuthority('p2')")
    public String r1() {
        String username = getUsername();
        return  username + " go r1 !";
    }

    @GetMapping(value = "/r/r2", produces = {"text/plain;charset=utf-8"})
    public String r2() {
        String username = getUsername();
        return  username + " go r2 !";
    }

    @GetMapping(value = "/r/r3", produces = {"text/plain;charset=utf-8"})
    public String r3() {
        String username = getUsername();
        return  username + " go r3 !";
    }

}
