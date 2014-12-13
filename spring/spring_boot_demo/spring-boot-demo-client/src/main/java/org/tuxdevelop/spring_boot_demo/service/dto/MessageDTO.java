package org.tuxdevelop.spring_boot_demo.service.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
public class MessageDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long messageId;
	@NotNull
	private String message;
	@NotNull
	private String senderUserName;
	@NotNull
	private String emailAddress;
	private Boolean read;

}
