package com.giorgiofederici.sjp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/architecture")
public class ArchitectureController {

	@GetMapping
	public String getArchitecture() {
		return "architecture";
	}

}
