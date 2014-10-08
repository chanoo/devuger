package com.devuger.front.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devuger.common.entities.Device;
import com.devuger.common.support.base.BaseController;
import com.devuger.common.support.base.BaseResult;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/devices")
public class DeviceController extends BaseController {

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public BaseResult add(@ModelAttribute @Validated Device device) {
		
		device = deviceService.add(device);
		
		BaseResult baseResult = new BaseResult("추가되었습니다.");
		baseResult.addAttribute(device);
		return baseResult;
	}
	
	@ResponseBody
	@RequestMapping(value = "/push", method = RequestMethod.POST)
	public BaseResult push(@ModelAttribute @Validated Device device) throws IOException {
		
		deviceService.push(device);
		
		return new BaseResult("메시지를 전송하였습니다.");
	}
}
