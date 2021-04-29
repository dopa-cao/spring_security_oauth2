package cn.com.clm.service;

import cn.com.clm.dto.AuthenticationRequest;
import cn.com.clm.dto.UserDto;

public interface AuthenticationService {

    UserDto authentication(AuthenticationRequest authenticationRequest);

}
