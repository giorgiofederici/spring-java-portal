package com.giorgiofederici.sjp.controller.user.social;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/user/social")
public class TwitterController {

	private Twitter twitter;

	private ConnectionRepository connectionRepository;

	@Inject
	public TwitterController(Twitter twitter, ConnectionRepository connectionRepository) {
		this.twitter = twitter;
		this.connectionRepository = connectionRepository;
	}

	@RequestMapping(value = "/twitter-connect", method = RequestMethod.GET)
	public String getTwitterIndex(Model model) {
		return "user/social/twitter-connect";
	}

	@RequestMapping(value = "/twitter-profile", method = RequestMethod.GET)
	public String getTwitterProfile(Model model) {
		if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
			return "redirect:/user/social/twitter-connect";
		}

		model.addAttribute(twitter.userOperations().getUserProfile());
		return "user/social/twitter-profile";
	}

	@RequestMapping(value = "/twitter-search", method = RequestMethod.GET)
	public String getTwitterSearch(Model model) {
		return "user/social/twitter-search-result";
	}
}
