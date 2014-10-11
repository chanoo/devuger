package com.devuger.front.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devuger.common.entities.Comment;
import com.devuger.common.entities.User;
import com.devuger.common.support.base.BaseController;
import com.devuger.common.support.base.BaseResult;
import com.devuger.front.common.session.UserSession;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/comments")
public class CommentController extends BaseController {

  /**
   * 코멘트 추가 수행
   * 
   * @param request
   * @param response
   * @param model
   * @return
   * @throws IOException
   */
  @ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public BaseResult add(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute Comment comment)
	throws IOException {
		
    User user = UserSession.isSignin(request);
    comment = commentService.add(comment, user);

	  BaseResult baseResult = new BaseResult("추가되었습니다.");
	  baseResult.addAttribute("comment", comment);
	  return baseResult;
	}
	
  /**
   * 코멘트 삭제 수행
   * 
   * @param request
   * @param response
   * @param id
   * @return
   * @throws IOException
   */
  @ResponseBody
  @RequestMapping(value = "/{id}/remove", method = RequestMethod.GET)
  public BaseResult remove(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  throws IOException {
    
    User user = UserSession.isSignin(request);
    commentService.remove(id, user);

    return new BaseResult("삭제되었습니다.");
  }
  
}