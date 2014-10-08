package com.devuger.front.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devuger.common.support.base.BaseController;
import com.devuger.common.support.base.BaseResult;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/messages")
public class MessagesController extends BaseController {

	@ResponseBody
	@RequestMapping(value = "/push", method = RequestMethod.POST)
	public BaseResult push(HttpServletRequest request, String message)
	throws ServletRequestBindingException {
		
//		Long id = ServletRequestUtils.getLongParameter(request, "shop.id");

//		Shop shop = shopService.get(id);
//		List<UserShop> userShops = messageService.sendPush(shop, message);
//		return new BaseResult(userShops.size() + "명에게 발송하였습니다.");
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/push", method = RequestMethod.GET)
	public BaseResult pushByDevice() {

//		List<Device> devices = messageService.sendPushByDevice(DeviceOs.ANDROID, "1.4.3으로 업그레이드 하셔야 쿠폰사용이 가능합니다.");

		BaseResult baseResult = new BaseResult();
//		baseResult.addAttribute(devices.size() + "명에게 발송 햇씁니다.");
		return baseResult;
	}
}