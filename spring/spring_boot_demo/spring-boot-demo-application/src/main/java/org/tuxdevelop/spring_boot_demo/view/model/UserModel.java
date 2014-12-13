package org.tuxdevelop.spring_boot_demo.view.model;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.tuxdevelop.spring_boot_demo.service.UserServiceBean;
import org.tuxdevelop.spring_boot_demo.service.dto.UserDTO;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserModel {

	@Getter
	private String userName;
	@Setter
	private UserDTO user;

	@Autowired
	private UserServiceBean userServiceBean;

	public UserModel() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		userName = authentication.getName();
	}

	public UserDTO getUser() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		userName = authentication.getName();
		return userServiceBean.getUserByUserName(userName);
	}

}
