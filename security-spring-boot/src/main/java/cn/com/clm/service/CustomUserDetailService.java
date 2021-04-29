package cn.com.clm.service;

import cn.com.clm.dao.UserDao;
import cn.com.clm.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("userName ==> " + username);

//        UserDetails userDetails = User.withUsername(username).password("123").authorities("p1").build();

        // user for db
        UserDto userDto = userDao.getUserByUsername(username);
        if (userDto == null) {
            return null;
        }

        // permission for db
        List<String> permissions = userDao.getPermissionsByUserId(userDto.getId());
        String[] permissionArr = new String[permissions.size()];
        permissions.toArray(permissionArr);

//        UserDetails userDetails = User.withUsername(userDto.getFullname()).password(userDto.getPassword())
//                .authorities("p1").build();

        UserDetails userDetails = User.withUsername(userDto.getFullname()).password(userDto.getPassword())
                .authorities(permissionArr).build();

        return userDetails;
    }

}
