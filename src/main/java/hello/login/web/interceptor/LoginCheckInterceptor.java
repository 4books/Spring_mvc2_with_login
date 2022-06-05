package hello.login.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static hello.login.web.SessionConst.LOGIN_MEMBER;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        log.info("로그인 인증 체크 인터셉처 실행 {}", requestURI);

        HttpSession session = request.getSession();
        if (session == null || session.getAttribute(LOGIN_MEMBER) == null) {
            log.info("로그인 미인증 사용자 요청");

            //로그인으로 redirect
            response.sendRedirect("/login?redirectURL=" + requestURI);
            return false;
        }

        log.info("로그인 인증 체크 인터셉처 종료 {}");


        return true;
    }
}