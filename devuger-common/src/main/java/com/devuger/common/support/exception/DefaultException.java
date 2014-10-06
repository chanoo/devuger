package com.devuger.common.support.exception;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class DefaultException implements HandlerExceptionResolver {

  private static final Logger logger = LoggerFactory.getLogger(DefaultException.class);

  public static String RESULT_ERROR = "error";
  private String view;

  public void setView(String view) {
    this.view = view;
  }

  public DefaultException(String view) {
    // TODO Auto-generated constructor stub
    this.view = view;
  }

  @Override
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex) {

    if (ex != null && logger.isErrorEnabled()) {
      @SuppressWarnings("unchecked")
      Enumeration<String> paramNames = request.getParameterNames();

      String url = request.getRequestURI();
      logger.error(String.format(">> ERROR: req.getRequestURI() = %s", url));

      while (paramNames.hasMoreElements()) {
        String name = paramNames.nextElement();
        String[] values = request.getParameterValues(name);
        for (String value : values) {
          String paramlog = String.format("req.getParameter(\"%s\") = %s", name, value);
          if (values.length > 1)
            paramlog = String.format("req.getParameter(\"%s[]\") = %s", name, value);
          logger.error(paramlog);
        }
      }
      String contentType = request.getContentType();
      logger.error(">> ERROR: req.getContentType() : " + contentType);

      Cookie[] cookies = request.getCookies();
      if (cookies != null) {
        for (Cookie cookie : cookies) {
          String name = cookie.getName();
          String value = cookie.getValue();
          String cookieString = String.format("req.getCookie(\"%s\") = %s", name, value);
          logger.error(cookieString);
        }
      }

      if (logger.isDebugEnabled())
        ex.printStackTrace();

      StackTraceElement stacks[] = ex.getStackTrace();
      for (StackTraceElement stack : stacks) {
        if (stack.getClassName().startsWith("com.devuger")) {
          logger.error("--[Exception] " + stack.getClassName() + "::" + stack.getMethodName() + ":(" + stack.getLineNumber() + ") : " + ex.getMessage());
        }
      }

      request.setAttribute("ex", ex);
    }

    String contentType = request.getContentType();
    if ((contentType != null && contentType.indexOf("json") >= 0) || request.getRequestURI().lastIndexOf(".json") >= 0) {
      if (ex.getClass().equals(HelloException.class)) {
        HelloException entEception = (HelloException) ex;
        String message = entEception.getMessage();
        String redirect = entEception.getRedirect();
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("result", RESULT_ERROR);
        modelMap.addAttribute("message", message);
        modelMap.addAttribute("redirect", redirect);

        return new ModelAndView(new MappingJackson2JsonView(), modelMap);
      } else {

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("result", RESULT_ERROR);
        modelMap.addAttribute("message", ex.getMessage());

        return new ModelAndView(new MappingJackson2JsonView(), modelMap);
      }
    }

    return new ModelAndView(view);
  }

}
