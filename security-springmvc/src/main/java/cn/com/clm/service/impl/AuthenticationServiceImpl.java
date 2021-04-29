package cn.com.clm.service.impl;

import cn.com.clm.dto.AuthenticationRequest;
import cn.com.clm.dto.UserDto;
import cn.com.clm.service.AuthenticationService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Override
    public UserDto authentication(AuthenticationRequest authenticationRequest) {
        if (authenticationRequest == null
                || authenticationRequest.getUsername() == null
                || authenticationRequest.getPassword() == null) {
            throw new RuntimeException("username or password is null");
        }
        UserDto userDto = getUserDto(authenticationRequest.getUsername());
        if (userDto == null) {
            throw new RuntimeException("user not found");
        }
        if (!userDto.getPassword().equals(authenticationRequest.getPassword())) {
            throw new RuntimeException("username or password not pass");
        }
        return userDto;
    }

    private UserDto getUserDto(String username) {
        return userMap.get(username);
    }

    private Map<String, UserDto> userMap = new HashMap<>();
    {
        Set<String> authorties1 = new HashSet<>();
        authorties1.add("p1");
        userMap.put("zhangsan", new UserDto("1001", "zhangsan", "123", "zhangsan", "12345", authorties1));

        Set<String> authorties2 = new HashSet<>();
        authorties2.add("p2");
        userMap.put("lisi", new UserDto("1002", "lisi", "123", "lisi", "1235", authorties2));
    }

}
