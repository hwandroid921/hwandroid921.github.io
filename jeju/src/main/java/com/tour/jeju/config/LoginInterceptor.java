package com.tour.jeju.config;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession(false);
        // 세션이 없거나 로그인 정보가 없으면
        if (session == null || session.getAttribute("loginMember") == null) {
            log.info("미인증 사용자 요청 - URI: {}", requestURI);
            // 로그인 페이지로 리다이렉트  return "redirect:/member/login"
            response.sendRedirect("/member/login?redirectURL=" + requestURI);
            return false;
        }
        return true;
    }
}
