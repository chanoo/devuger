package com.devuger.front.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devuger.common.support.base.BaseController;
import com.devuger.common.support.base.BaseResult;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

  /**
   * 코멘트 추가
   * 
   * @param request
   * @param response
   * @param model
   * @return
   * @throws IOException
   */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public BaseResult view(HttpServletRequest request, HttpServletResponse response, Model model)
	throws IOException {

	  return new BaseResult("작성 되었습니다.");
	}
	
}