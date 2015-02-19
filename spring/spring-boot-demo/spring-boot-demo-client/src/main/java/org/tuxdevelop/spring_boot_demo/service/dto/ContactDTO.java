package org.tuxdevelop.spring_boot_demo.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@XmlRootElement
public class ContactDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long contactId;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	private String zipCode;
	@NotNull
	private String city;
	@NotNull
	private String streetLine;

}
