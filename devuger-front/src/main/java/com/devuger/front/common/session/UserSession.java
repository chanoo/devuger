package com.devuger.front.common.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.util.Assert;

import com.devuger.common.entities.User;
import com.devuger.common.support.base.BaseObject;
import com.devuger.common.support.constant.GlobalConst;

public class UserSession extends BaseObject {

	/**
	 * 세션 정보 가져오기
	 * 
	 * @param request
	 * @return
	 */
	public static User get(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		return (User) session.getAttribute(GlobalConst.USER_SESSION);
	}
	
	/**
	 * 로그인 여부 확인
	 * 
	 * @param request
	 * @return
	 */
  public static User isSignin(HttpServletRequest request) {

    HttpSession session = request.getSession();
    User user = (User) session.getAttribute(GlobalConst.USER_SESSION);
    Assert.notNull(user, "로그인이 필요합니다.");
    return user;
  }

	/**
	 * 로그인
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	public static User signin(HttpServletRequest request, User user) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
    session.removeAttribute(GlobalConst.USER_SESSION);
		session.setAttribute(GlobalConst.USER_SESSION, user);
		return user;
	}

	/**
	 * 세션 제거
	 * 
	 * @param request
	 */
	public static void signout(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.removeAttribute(GlobalConst.USER_SESSION);
	}
}
