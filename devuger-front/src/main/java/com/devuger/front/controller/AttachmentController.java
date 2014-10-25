package com.devuger.front.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devuger.common.entities.Attachment;
import com.devuger.common.entities.User;
import com.devuger.common.support.base.BaseController;
import com.devuger.common.support.base.BaseResult;
import com.devuger.front.common.session.UserSession;

@Controller
@RequestMapping("/attachments")
public class AttachmentController extends BaseController {

	// 다운로드
	@RequestMapping(value = "/{id}/down", method = RequestMethod.GET)
	public void down(HttpServletResponse response, @PathVariable Long id)
	throws IOException {
		Attachment attachment = attachmentService.get(id);
		attachmentService.download(response, attachment);
	}
	
	// 비율에 맞게 썸네일 가져오기
	@RequestMapping(value = "/{id}/ratio/{size}", method = RequestMethod.GET)
	public void ratio(HttpServletResponse response, @PathVariable Long id, @PathVariable int size)
	throws IOException {
		Attachment attachment = attachmentService.get(id);
		attachmentService.thumbnail(response, attachment, size, size, true);
	}
	
	// Thumbnail 다운로드
	@RequestMapping(value = "/{id}/thumbnail/{size}", method = RequestMethod.GET)
	public void thumbnail(HttpServletResponse response, @PathVariable Long id, @PathVariable int size)
	throws IOException {
		Attachment attachment = attachmentService.get(id);
		attachmentService.thumbnail(response, attachment, size, size, false);
	}
	
	@RequestMapping(value = "/{id}/thumbnail/{width}/{height}", method = RequestMethod.GET)
	public void thumbnail(HttpServletResponse response, @PathVariable Long id, @PathVariable int width, @PathVariable int height)
	throws IOException {
		Attachment attachment = attachmentService.get(id);
		attachmentService.thumbnail(response, attachment, width, height, false);
	}

	// 업로드
	@ResponseBody
	@RequestMapping(value = "/upload/{tableName}/{tableColumn}", method = RequestMethod.POST)
	public BaseResult uploadWithoutId(HttpServletRequest request, HttpServletResponse response, @ModelAttribute @Validated Attachment attachment, @PathVariable String tableName, @PathVariable String tableColumn)
	throws NoSuchAlgorithmException, IOException
	{
    User user = UserSession.isSignin(request);
		attachment = attachmentService.upload(user, attachment, tableName, tableColumn);

		BaseResult baseResult = new BaseResult("업로드되었습니다.");
		baseResult.addAttribute(attachment);
		return baseResult;
	}

	// 업로드
	@ResponseBody
	@RequestMapping(value = "/upload/{tableName}/{tableColumn}/{id}", method = RequestMethod.POST)
	public BaseResult upload(HttpServletRequest request, HttpServletResponse response, Attachment attachment, @PathVariable String tableName, @PathVariable String tableColumn, @PathVariable Long id)
	throws NoSuchAlgorithmException, IOException
	{
		User user = UserSession.isSignin(request);
		attachment = attachmentService.upload(user, attachment, tableName, tableColumn, id);

		BaseResult baseResult = new BaseResult("업로드되었습니다.");
		baseResult.addAttribute(attachment);
		return baseResult;
	}
	
	// 삭제
	@ResponseBody
	@RequestMapping(value = "/{id}/remove", method = RequestMethod.GET)
	public BaseResult delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
	throws IOException {
		
    User user = UserSession.isSignin(request);
		attachmentService.delete(user, id);
		return new BaseResult("삭제되었습니다.");
	}

}
