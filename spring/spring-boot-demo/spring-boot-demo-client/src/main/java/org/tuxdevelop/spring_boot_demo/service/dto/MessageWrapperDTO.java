package org.tuxdevelop.spring_boot_demo.service.dto;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.Collection;

@Data
@XmlRootElement
public class MessageWrapperDTO {

	private Collection<MessageDTO> messageDTOs;
}
