package com.giorgiofederici.sjp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = { "/", "/index" })
public class IndexController {

	@RequestMapping
	public String getIndex() {
		return "index";
	}

}
