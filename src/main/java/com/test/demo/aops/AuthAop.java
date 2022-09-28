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
