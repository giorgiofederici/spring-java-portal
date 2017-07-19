package com.giorgiofederici.sjp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/showcases")
public class ShowcasesController {

	@GetMapping
	public String getShowcases() {
		return "showcases";
	}

	@GetMapping(value = "/ocs")
	public String getOnlineCartSystem() {
		return "showcases/ocs";
	}
}
