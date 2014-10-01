package com.devuger.front.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devuger.common.dto.OpenGraph;
import com.devuger.common.support.base.BaseController;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends BaseController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Model model) {
	  
	  HttpSession httpSession = request.getSession();
	  OpenGraph og = (OpenGraph) httpSession.getAttribute("og");
	  model.addAttribute("og", og);
    httpSession.removeAttribute("og");

		return "index";
	}
}
