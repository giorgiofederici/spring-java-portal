package com.giorgiofederici.sjp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/technologies")
public class TechnologiesController {

	@RequestMapping(method = RequestMethod.GET)
	public String getTechnologies() {
		return "technologies";
	}

	@RequestMapping(value = "/database", method = RequestMethod.GET)
	public String getTechsDatabase() {
		return "techs-database";
	}

	@RequestMapping(value = "/hibernate", method = RequestMethod.GET)
	public String getTechsHibernate() {
		return "techs-hibernate";
	}

}
