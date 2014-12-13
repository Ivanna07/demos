package org.tuxdevelop.spring_boot_demo.view.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.tuxdevelop.spring_boot_demo.service.UserServiceBean;
import org.tuxdevelop.spring_boot_demo.service.dto.UserDTO;
import org.tuxdevelop.spring_boot_demo.view.model.RegisterModel;

@Controller
@Slf4j
public class RegisterController {

	@Autowired
	private RegisterModel registerModel;
	@Autowired
	private UserServiceBean userServiceBean;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void getRegisterModel(final Model model) {
		model.addAttribute("registerModel", registerModel);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute final RegisterModel registerModel) {
		log.info("creating user: " + registerModel.getUser());
		final UserDTO result = userServiceBean.addUser(registerModel.getUser());
		log.info("created user: " + result);
		return "redirect:/login";
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(final HttpServletRequest req, final Exception exception) {
		log.error("Request: " + req.getRequestURL() + " raised " + exception);
		final ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL());
		mav.addObject("timestamp", new Date());
		mav.setViewName("error");
		return mav;
	}

}
