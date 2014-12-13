package org.tuxdevelop.spring_boot_demo.service.dto;

import java.util.Collection;

import lombok.Data;

@Data
public class MessageWrapperDTO {
	private Collection<MessageDTO> collection;
}
