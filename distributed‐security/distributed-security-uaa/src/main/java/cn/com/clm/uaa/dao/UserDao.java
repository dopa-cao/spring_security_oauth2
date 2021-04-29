package cn.com.clm.uaa.dao;

import cn.com.clm.uaa.model.PermissionDto;
import cn.com.clm.uaa.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserDto getUserByUsername(String username) {
        String sql = "select id,username,password,fullname,mobile from t_user where username = ?";
        List<UserDto> userDtos = jdbcTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper<>(UserDto.class));
        if (userDtos == null && userDtos.size() <= 0) {
            return null;
        }
        return userDtos.get(0);
    }

    public List<String> getPermissionsByUserId(String userId) {
        String sql = "select * from t_permission where id in (select permission_id from t_role_permission where role_id in (select role_id from t_user_role where user_id = ?))";
        List<PermissionDto> permissionDtos = jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(PermissionDto.class));
        List<String> permissions = new ArrayList<>(permissionDtos.size());
        permissionDtos.stream().iterator().forEachRemaining(c -> permissions.add(c.getCode()));
        return permissions;
    }

}
