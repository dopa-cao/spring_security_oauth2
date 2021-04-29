package cn.com.clm.order.controller;

import cn.com.clm.order.model.UserDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping(value = "/r1")
    @PreAuthorize("hasAnyAuthority('p1')")
    public String r1() {
        UserDTO userDTO = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDTO.getFullname() + " go to resource 1";
    }

    @GetMapping(value = "/r2")
    @PreAuthorize("hasAnyAuthority('p2')")
    public String r2() {
        UserDTO userDTO = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDTO.getFullname() + " go to resource 2";
    }


}
