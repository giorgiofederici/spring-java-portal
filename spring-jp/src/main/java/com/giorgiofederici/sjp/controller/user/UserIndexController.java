package com.giorgiofederici.sjp.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/user/index")
public class UserIndexController {

	@RequestMapping(method = RequestMethod.GET)
	public String getUserIndex() {
		return "user/index";
	}

}
