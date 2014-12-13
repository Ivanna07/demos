package org.tuxdevelop.spring_boot_demo.service.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long userId;
	@NotNull
	private String userName;
	@NotNull
	private String password;
	@NotNull
	private String emailAddress;
	@NotNull
	private ContactDTO contactDTO;
}
