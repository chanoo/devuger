package com.devuger.front.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devuger.common.entities.Feed;
import com.devuger.common.entities.Like;
import com.devuger.common.entities.User;
import com.devuger.common.support.base.BaseController;
import com.devuger.common.support.base.BaseResult;
import com.devuger.front.common.session.UserSession;

/**
 * 피드 관련 컨트롤러
 *  - 피드 추가
 *  - 피드 삭제
 *  - 피드 전체 가져오기
 * 
 * @author hello
 *
 */
@Controller
@RequestMapping("/feeds")
public class FeedController extends BaseController {

  /**
   * 피드 추가
   * 
   * @param request
   * @param response
   * @param model
   * @return
   * @throws IOException
   */
  @ResponseBody
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public BaseResult add(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute Feed feed)
  throws IOException {

    User user = UserSession.isSignin(request);
    feed = feedService.add(feed, user);

    return new BaseResult("작성 되었습니다.");
  }

  /**
   * 피드 삭제하기
   * 
   * @param request
   * @param response
   * @param id 피드 PK
   * @return
   * @throws IOException
   */
  @ResponseBody
  @RequestMapping(value = "/{id}/remove", method = RequestMethod.GET)
  public BaseResult remove(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  throws IOException {

    User user = UserSession.isSignin(request);
    feedService.remove(id, user);

    return new BaseResult("삭제 되었습니다.");
  }
	
  /**
   * 
   * 
   * @param request
   * @param response
   * @param id
   * @return
   * @throws IOException
   */
  @ResponseBody
  @RequestMapping(value = "/{id}/like", method = RequestMethod.GET)
  public BaseResult like(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  throws IOException {

    User user = UserSession.isSignin(request);
    Feed feed = feedService.get(id);
    likeService.add(feed, user);
    List<Like> likes = likeService.getByFeed(feed);
    feed = feedService.setLikeCount(feed, likes.size());

    BaseResult baseResult = new BaseResult("좋아요 했습니다.");
    baseResult.addAttribute("feed", feed);
    return baseResult;
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
    Page<Feed> feeds = feedService.getAll(page);

    BaseResult baseResult = new BaseResult(feeds.getTotalElements() + "개 가져왔습니다.");
    baseResult.addAttribute("feeds", feeds);
    return baseResult;
  }
}