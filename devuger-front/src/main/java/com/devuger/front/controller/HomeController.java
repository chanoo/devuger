package com.devuger.front.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devuger.common.entities.Attachment;
import com.devuger.common.entities.Feed;
import com.devuger.common.entities.User;
import com.devuger.common.support.base.BaseController;
import com.devuger.common.support.base.BaseResult;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends BaseController {
	
  /**
   * 
   * 
   * @param request
   * @param model
   * @return
   */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Model model) {
	  
	  int page = ServletRequestUtils.getIntParameter(request, "page", 1);

	  User user = new User();
	  user.setId(1L);
    List<Feed> feeds = feedService.getByUser(user, page);
    model.addAttribute("feeds", feeds);
    
		return "index";
	}


  @ResponseBody
  @RequestMapping(value = "/opengraph")
  public BaseResult signin(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    User user = userService.signup(null);

    String getUrl = request.getParameter("url");

    Assert.hasText(getUrl, "URL 주소를 입력 해주세요.");

    List<String> urls = new ArrayList<String>();
    List<Integer> dapthList = new ArrayList<Integer>();
    urls.add(getUrl);
    dapthList.add(0);

    List<Attachment> attachments = new ArrayList<Attachment>();

    // URL 정보 가져오기
    BaseResult baseResult = new BaseResult();
    Document doc = null;

    boolean isOpenGraph = false;
    for (int i = 0; i < urls.size(); i++) {

      this.getLogger().debug(">>>>>>> urls.size() : " + urls.size());

      String url = urls.get(i);
      int depth = dapthList.get(i);
      this.getLogger().debug(">>>>>>> urls: " + url);

      String domain = parseDomain(url);

      try {
        doc = Jsoup.connect(url.trim()).get();
      } catch (IOException e) {
        this.getLogger().error(e.getMessage());
        continue;
      }

      Elements elements = doc.select("frame");
      Elements iframes = doc.select("iframe");
      elements.addAll(iframes);

      for (Element frame : elements) {
        String src = frame.attr("src");
        this.getLogger().debug("frame src:" + src);

        if (src != null && src.trim().length() > 0) {
          if (src.startsWith("/") || src.startsWith("./")) {
            if (depth < 5) {
              urls.add(urls.size(), String.format("%s%s", domain, src));
              dapthList.add(depth + 1);
            }
          } else if (src.startsWith("http")) {
            if (depth < 5) {
              urls.add(urls.size(), src);
              dapthList.add(depth + 1);
            }
          } else if (src.startsWith("about:blank")) {
            continue;
          }
        }
      }

      Elements media = doc.select("[src]");
      for (Element src : media) {
        if (src.tagName().equals("img")) {
          String imgSrc = src.attr("abs:src");
          try {
            URL fileUrl = new URL(imgSrc);
            URLConnection connection = fileUrl.openConnection();
            InputStream httpInputStream = connection.getInputStream();

            final BufferedInputStream bufferedInputStream = new BufferedInputStream(httpInputStream);
            bufferedInputStream.mark(Integer.MAX_VALUE);

            BufferedImage bimg = ImageIO.read(bufferedInputStream);

            if (bimg != null) {
              int width = bimg.getWidth();
              int height = bimg.getHeight();
              if (width > 300 && height > 300) {
                bufferedInputStream.reset();
                attachments.add(attachmentService.upload(user, connection, bufferedInputStream, "products", "main", null, 0, width, height, null));
              }

              bufferedInputStream.close();
              bimg.flush();

              httpInputStream.close();

            } else {
              this.getLogger().debug(">>>>> BufferedImage is null");
            }
          } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            this.getLogger().error(">>  " + imgSrc + " :: " + e.getMessage());
          } catch (IOException e) {
            // TODO Auto-generated catch block
            this.getLogger().error(">> " + imgSrc + " :: " + e.getMessage());
          }
        }
      }

      if (!isOpenGraph) {
        ModelMap model = html(doc);
        if (model == null) {
          continue;
        } else {

          if (model.get("url") == null)
            model.addAttribute("url", getUrl);

          baseResult.addAttribute("og", model);
          isOpenGraph = true;
        }
      }
    }

    baseResult.addAttribute("attachments", attachments);

    return baseResult;
  }

  private String parseDomain(String url) throws MalformedURLException {

    URL parseUrl = new URL(url);
    String protocol = parseUrl.getProtocol();
    String authority = parseUrl.getAuthority();
    return String.format("%s://%s", protocol, authority);
  }

  private ModelMap html(Document doc) throws IOException {

    ModelMap model = null;
    for (Element meta : doc.select("meta")) {

      String property = meta.attr("property");
      String name = meta.attr("name");
      String content = meta.attr("content");

      if (property != null && property.trim().length() > 0) {

        if (model == null)
          model = new ModelMap();

        model.addAttribute(property.replace("og:", ""), content);
      }

      if (name != null && name.trim().length() > 0) {

        if (name.equals("description")) {

          if (model == null)
            model = new ModelMap();

          model.addAttribute(name, content);
        }

      }

      this.getLogger().debug(String.format("%s : %s", property, content));

    }

    if (model != null && model.get("title") == null) {
      Element title = doc.select("title").first();
      model.addAttribute("title", title.html());
    }

    return model;
  }
}
