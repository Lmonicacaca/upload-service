package com.mbr.upload.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LogInterceptor implements HandlerInterceptor {
	Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object handler) throws Exception {
		try {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			String methodFullName = handlerMethod.getBeanType().getName() + "." + method.getName();
			logger.info(methodFullName);
		} catch (Exception e) {
			logger.error("日志拦截器打印异常", e);
		}
		return true;
	}

}
