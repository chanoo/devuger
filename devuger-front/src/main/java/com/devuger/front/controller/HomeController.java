package com.devuger.front.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devuger.common.entities.Feed;
import com.devuger.common.entities.User;
import com.devuger.common.support.base.BaseController;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends BaseController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Model model) {
	  
	  int page = ServletRequestUtils.getIntParameter(request, "page", 1);

	  User user = new User();
	  user.setId(1L);
    List<Feed> feeds = feedService.getByUser(user, page);
    model.addAttribute("feeds", feeds);
    
		return "index";
	}
}
