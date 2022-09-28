package com.test.demo.aops;

import java.security.AccessControlException;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.test.demo.auth.SessionService;

import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthAop {

    private final SessionService sessionService;

    /**
     * HTTP Request 요청이 들어왔을 시 요청 헤더의 "Authorization"에 담겨있는 Token 을 읽어와 해당 Token 이
     * 유효한 Token인지 확인 후
     * 유효하지 못한 토큰일 경우 AccessControlException 을 발생시킵니다.
     * 사용을 위해서는 원하는 메소드에 @Auth 어노테이션을 붙이시면 사용가능합니다.
     * 
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "@annotation(Auth)")
    public Object tokenValidate(ProceedingJoinPoint joinPoint) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();

        String token = Objects.requireNonNull(request.getHeader("Authorization"), "Authorization header is null");

        if (sessionService.tokenValidate(token)) {
            return joinPoint.proceed(joinPoint.getArgs());
        } else {
            throw new AccessControlException("Invalid token.");
        }
    }

}
