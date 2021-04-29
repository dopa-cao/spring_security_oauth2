package cn.com.clm.order.filter;

import cn.com.clm.order.model.UserDTO;
import cn.com.clm.order.util.EncryptUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String enToken = httpServletRequest.getHeader("json-token");
        if (enToken != null) {
            String json = EncryptUtil.decodeUTF8StringBase64(enToken);
            JSONObject userJson = JSON.parseObject(json);
//            UserDTO userDTO = new UserDTO();
//            userDTO.setUsername(userJson.getString("principal"));

            String principal = userJson.getString("principal");
            UserDTO userInfo = JSON.parseObject(principal, UserDTO.class);

            JSONArray authoritiesArr = userJson.getJSONArray("authorities");
            String[] authorities = authoritiesArr.toArray(new String[authoritiesArr.size()]);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userInfo, null, AuthorityUtils.createAuthorityList(authorities));
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
