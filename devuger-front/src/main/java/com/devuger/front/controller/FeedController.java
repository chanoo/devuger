package com.devuger.front.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devuger.common.entities.Feed;
import com.devuger.common.entities.User;
import com.devuger.common.support.base.BaseController;
import com.devuger.common.support.base.BaseResult;
import com.devuger.front.common.session.UserSession;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/feeds")
public class FeedController extends BaseController {

  /**
   * 코멘트 추가
   * 
   * @param request
   * @param response
   * @param model
   * @return
   * @throws IOException
   */
  @ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public BaseResult view(HttpServletRequest request, HttpServletResponse response, Model model, @UserAuth User user, @ModelAttribute Feed feed)
	throws IOException {

	  user = UserSession.isSignin(request);
	  feed = feedService.add(feed, user);

	  return new BaseResult("작성 되었습니다.");
	}
	
	/**
	 * 리드리스트 가져오기
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
  @RequestMapping(method = RequestMethod.GET)
  public BaseResult index(HttpServletRequest request, Model model) {

    int page = ServletRequestUtils.getIntParameter(request, "page", 1);

    String type = ServletRequestUtils.getStringParameter(request, "type", "feed");

    Assert.isTrue(type.equals("feed"), "에러에러");
    
    User user = new User();
    user.setId(1L);
    List<Feed> feeds = feedService.getByUser(user, page);

    BaseResult baseResult = new BaseResult(feeds.size() + "개 가져왔습니다.");
    baseResult.addAttribute("feeds", feeds);
    return baseResult;
  }
}