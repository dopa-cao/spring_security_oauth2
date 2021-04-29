package cn.com.clm.interceptor;

import cn.com.clm.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class SimpleAuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object attribute = session.getAttribute(UserDto.SESSION_USER_KEY);
        if (attribute == null) {
            writeContent(response, "please go to login !");
        }
        UserDto userDto = (UserDto) attribute;
        String requestURI = request.getRequestURI();
        if (userDto.getAuthorities().contains("p1") && requestURI.contains("/r1")) {
            return true;
        }
        if (userDto.getAuthorities().contains("p2") && requestURI.contains("/r2")) {
            return true;
        }
        writeContent(response, "not enough permission !");

        return false;
    }

    private void writeContent(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.println(msg);
        printWriter.close();
        response.resetBuffer();
    }

}
