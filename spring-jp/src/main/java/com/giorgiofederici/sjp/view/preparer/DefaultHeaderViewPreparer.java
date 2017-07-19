package com.giorgiofederici.sjp.view.preparer;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.giorgiofederici.sjp.domain.entity.UserProfileImage;
import com.giorgiofederici.sjp.service.UserService;

public class DefaultHeaderViewPreparer implements ViewPreparer {

	@Autowired
	private UserService userService;

	@Override
	public void execute(Request tilesRequest, AttributeContext attributeContext) {

		Object userObj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;

		if (userObj instanceof String) {
			username = (String) userObj;
		} else if (userObj instanceof User) {
			User user = (User) userObj;
			username = user.getUsername();
		} else {
			username = "";
		}

		if (username != null && !username.trim().isEmpty()) {
			UserProfileImage userProfileImage = this.userService.getUserProfileImageByUsername(username);
			if (userProfileImage != null) {
				byte[] encoded = Base64.encodeBase64(userProfileImage.getContent());
				try {
					String base64DataString = new String(encoded, "UTF-8");
					attributeContext.putAttribute("userProfileImage", new Attribute(base64DataString));
				} catch (UnsupportedEncodingException e) {

				}
			}
		}
	}
}
