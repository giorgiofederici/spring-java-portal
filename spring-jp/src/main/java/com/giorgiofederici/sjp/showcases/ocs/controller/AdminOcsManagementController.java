package com.giorgiofederici.sjp.showcases.ocs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin/ocs")
public class AdminOcsManagementController {

	@GetMapping
	public String getOcsManagement() {
		return "admin/ocs";
	}

}
