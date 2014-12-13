package org.tuxdevelop.spring_boot_demo.view.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.tuxdevelop.spring_boot_demo.service.MessageServiceBean;
import org.tuxdevelop.spring_boot_demo.service.dto.MessageDTO;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MessageModel {

	@Autowired
	private MessageServiceBean messageServiceBean;
	@Autowired
	private UserModel userModel;

	private List<MessageDTO> inBox;
	private List<MessageDTO> outBox;

	public List<MessageDTO> getInBox() {
		inBox = messageServiceBean.getAllReceivedMessagesByUserName(userModel.getUserName());
		return inBox;
	}

	public List<MessageDTO> getOutBox() {
		outBox = messageServiceBean.getAllSendMessagesByUserName(userModel.getUserName());
		return outBox;
	}

}
