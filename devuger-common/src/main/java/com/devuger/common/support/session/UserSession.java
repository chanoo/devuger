package com.devuger.common.support.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.devuger.common.entities.User;
import com.devuger.common.support.base.BaseObject;
import com.devuger.common.support.constant.GlobalConst;

public class UserSession extends BaseObject {

	// 로그인 여부 확인
	public static boolean isLogin(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(GlobalConst.USER_SESSION);
		if(user == null || user.getId() == null)
			return false;

		return true;
	}

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
	
	public static User signin(HttpServletRequest request, User user) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
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
	
	/**
	 * 세션 업데이트
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	public static User update(HttpServletRequest request, User user) {
		HttpSession session = request.getSession();
		session.removeAttribute(GlobalConst.USER_SESSION);
		session.setAttribute(GlobalConst.USER_SESSION, user);
		return user;
	}
}
