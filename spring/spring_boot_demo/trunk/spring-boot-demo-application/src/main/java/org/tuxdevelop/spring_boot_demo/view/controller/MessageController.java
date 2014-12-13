package org.tuxdevelop.spring_boot_demo.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tuxdevelop.spring_boot_demo.view.model.MessageModel;
import org.tuxdevelop.spring_boot_demo.view.model.UserModel;

@Controller
public class MessageController {

    @Autowired
    private UserModel userModel;

    @Autowired
    private MessageModel messageModel;

    @RequestMapping(value="/message",method = RequestMethod.GET)
    public String getMessage(final Model model) {
        return "redirect:/inbox";
    }

    @RequestMapping(value="/inbox",method = RequestMethod.GET)
    public void getInBox(final Model model) {
        model.addAttribute(messageModel);
        model.addAttribute(userModel);
    }

    @RequestMapping(value="/outbox",method = RequestMethod.GET)
    public void getOutBox(final Model model) {
        model.addAttribute(messageModel);
        model.addAttribute(userModel);
    }

}
