package com.devuger.front.interceptor;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.devuger.common.entities.User;
import com.devuger.front.common.session.UserSession;

@Component
public class LoggingHandlerInterceptor extends HandlerInterceptorAdapter {

  private final Logger logger = Logger.getLogger(this.getClass());

  @Autowired
  private JpaTransactionManager transactionManager;

  private ThreadLocal<TransactionStatus> threadLocal = new ThreadLocal<TransactionStatus>();

  public ThreadLocal<TransactionStatus> getThreadLocal() {
    return threadLocal;
  }

  public void setThreadLocal(ThreadLocal<TransactionStatus> threadLocal) {
    this.threadLocal = threadLocal;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {

    String url = request.getRequestURI();
    request.setAttribute("url", url);

    // 쿠키값에서 제품 번호 찾기
    Cookie[] cookies = request.getCookies();

    if (logger.isDebugEnabled()) {
      String uriLog = String.format("req.getRequestURI() = %s", request.getRequestURI());
      logger.debug(uriLog);

      Enumeration<?> paramNames = request.getParameterNames();
      while (paramNames.hasMoreElements()) {
        String name = (String) paramNames.nextElement();
        String[] values = request.getParameterValues(name);
        for (String value : values) {
          String paramlog = String.format("req.getParameter(\"%s\") = %s", name, value);
          if (values.length > 1)
            paramlog = String.format("req.getParameter(\"%s\") = %s", name, value);
          logger.debug(paramlog);
        }
      }
      String method = request.getMethod();
      logger.debug("req.getMethod() : " + method);
      String contentType = request.getContentType();
      logger.debug("req.getContentType() : " + contentType);

      if (cookies != null) {
        for (Cookie cookie : cookies) {
          String name = cookie.getName();
          String value = cookie.getValue();
          String cookieString = String.format("req.getCookie(\"%s\") = %s", name, value);
          logger.debug(cookieString);
        }
      }
    }

    User user = UserSession.get(request);
    request.setAttribute("user", user);

    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("sessionTransaction");
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    TransactionStatus status = transactionManager.getTransaction(def);
    getThreadLocal().set(status);

    return super.preHandle(request, response, obj);
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    TransactionStatus status = getThreadLocal().get();
    if (!status.isCompleted()) {
      transactionManager.commit(status);
    }
    String contextPath = request.getContextPath();
    request.setAttribute("contextPath", contextPath);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex) throws Exception {
    TransactionStatus status = getThreadLocal().get();
    if (!status.isCompleted()) {
      transactionManager.rollback(status);
      logger.error(">>> rollback");
      if (ex != null)
        logger.error(">>> message: " + ex.getMessage());
    }
    getThreadLocal().remove();
    super.afterCompletion(request, response, obj, ex);
  }
}
