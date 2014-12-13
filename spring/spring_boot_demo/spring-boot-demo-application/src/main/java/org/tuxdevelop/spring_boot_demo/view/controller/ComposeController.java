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
import org.tuxdevelop.spring_boot_demo.service.MessageServiceBean;
import org.tuxdevelop.spring_boot_demo.service.dto.MessageDTO;
import org.tuxdevelop.spring_boot_demo.view.model.ComposeModel;
import org.tuxdevelop.spring_boot_demo.view.model.UserModel;

@Slf4j
@Controller
public class ComposeController {

	@Autowired
	private UserModel userModel;
	@Autowired
	private ComposeModel composeModel;
	@Autowired
	private MessageServiceBean messageServiceBean;

	@RequestMapping(value = "/compose", method = RequestMethod.GET)
	public UserModel getUser(final Model model) {
		model.addAttribute("userModel", userModel);
		model.addAttribute("composeModel", composeModel);
		return userModel;
	}

	@RequestMapping(value = "/compose", method = RequestMethod.POST)
	public String sendMessage(@ModelAttribute final ComposeModel composeModel) {
		final MessageDTO messageDTO = new MessageDTO();
		messageDTO.setSenderUserName(userModel.getUserName());
		messageDTO.setMessage(composeModel.getMessageText());
		messageDTO.setEmailAddress(composeModel.getEmailAddress());
		messageServiceBean.sendMessage(messageDTO);
		return "redirect:/message";
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
