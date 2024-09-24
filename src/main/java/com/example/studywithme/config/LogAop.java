package com.example.studywithme.config;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import com.example.studywithme.dto.UserDto;

import jakarta.servlet.http.HttpSession;

@Aspect
@Component
public class LogAop {
	Logger log = org.slf4j.LoggerFactory.getLogger("visitedLogger");
	@Pointcut("execution(* com.example.studywithme.controller.StudyPostController.getStudyPostById(..))")
	private void cut(){}

	// Pointcut에 의해 필터링된 경로로 들어오는 경우 메서드 호출 전에 적용
	@Before("cut()")
	public void beforeParameterLog(JoinPoint joinPoint) {

		// 파라미터 받아오기
		Object[] args = joinPoint.getArgs();
		String userId = null;
		int postId = -1;
		for (Object arg : args) {
			if(arg instanceof HttpSession) {
				UserDto userDto = (UserDto) ((HttpSession) arg).getAttribute("userDto");
				if(userDto != null) {
					userId = userDto.getUserId();
				}
			}else{
				postId = (int) arg;
			}
		}
		if(postId!=-1 ) {
			log.info("visitedLog user = {} postId = {}", userId , postId);
		}
	}

}
