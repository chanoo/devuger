package com.devuger.front.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devuger.common.entities.User;
import com.devuger.common.support.base.BaseController;
import com.devuger.common.support.base.BaseResult;
import com.devuger.common.validation.SignupValidator;
import com.devuger.front.common.session.UserSession;

/**
 * 사용자 관련 컨트롤러
 *  - 사용자 가입 화면
 *  - 사용자 가입 수행
 *  - 사용자 로그인 화면
 *  - 사용자 로그인 수행
 *  - 사용자 로그아웃 수행
 * 
 * @author hello
 *
 */
@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

  /**
   * 사용자 가입 화면
   * 
   * @param request
   * @param response
   * @param model
   * @param user
   * @return
   */
  @RequestMapping(value = "/signup", method = RequestMethod.GET)
  public String signupView(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute User user) {
    return "none/users.signup";
  }
  
  /**
   * 사용자 가입 수행
   * 
   * @param request
   * @param response
   * @param model
   * @param user
   * @return
   */
  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  public String signup(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute User user, BindingResult bindingResult) {

    new SignupValidator().validate(user, bindingResult); //validation을 수행한다.
    if(bindingResult.hasErrors()){ //validation 에러가 있으면,
      this.getLogger().error("Validation Error, 가입 실패");
      return "none/users.signup";
    }

    try {
      String ip = request.getRemoteAddr();
      user = userService.signup(user, ip);
    } catch (Exception e) {
      model.addAttribute("message", e.getLocalizedMessage());
      return "none/users.signup";
    }

    return "none/users.signup.ok";
  }

  /**
   * 사용자 로그인 화면
   * 
   * @param request
   * @param response
   * @param model
   * @param user
   * @return
   */
  @RequestMapping(value = "/signin", method = RequestMethod.GET)
  public String signinView(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute User user)
  {
    return "none/users.signin";
  }

  /**
   * 사용자 로그인
   * 
   * @param request
   * @param response
   * @param model
   * @param user 사용자 정보
   * @return
   */
  @ResponseBody
	@RequestMapping(value = "/signin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public BaseResult signin(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute User user)
  {
    String email = user.getEmail();
    String hashedPassword = user.getHashedPassword();
    String ip = request.getRemoteAddr();
    user = userService.signin(email, hashedPassword, ip);
    
    UserSession.signin(request, user); // 세션 저장

    BaseResult baseResult = new BaseResult("로그인되었습니다.");
    baseResult.addAttribute("user", user);
	  return baseResult;
	}
	
	/**
	 * 사용자 로그아웃
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
  @RequestMapping(value = "/signout", method = RequestMethod.GET)
  public String signout(HttpServletRequest request, HttpServletResponse response, Model model)
  {
    UserSession.signout(request);
    return String.format("redirect:%s/", request.getContextPath());
  }
}