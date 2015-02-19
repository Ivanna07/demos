package org.tuxdevelop.spring_boot_demo.view.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tuxdevelop.spring_boot_demo.service.UserServiceBean;
import org.tuxdevelop.spring_boot_demo.view.model.UpdateUserModel;
import org.tuxdevelop.spring_boot_demo.view.model.UserModel;

@Slf4j
@Controller
public class UserController {

	@Autowired
	private UserModel userModel;

	@Autowired
	private UserServiceBean userServiceBean;

	@Autowired
	private UpdateUserModel updateUserModel;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Model getUser(final Model model) {
		model.addAttribute("userModel", userModel);
		updateUserModel.setUser(userModel.getUser());
		model.addAttribute("updateUserModel", updateUserModel);
		return model;
	}

	@ModelAttribute
	public Model getUpdateUserModel(final Model model) {
		model.addAttribute("updateUserModel", updateUserModel);
		return model;
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute(value = "updateUserModel") final UpdateUserModel updateUserModel,
			final Model model) {
		log.info("Updating user: " + updateUserModel.getUser());
		userServiceBean.updateUser(updateUserModel.getUser());
		model.addAttribute("updateUserModel", updateUserModel);
		return "redirect:/user";
	}
}
