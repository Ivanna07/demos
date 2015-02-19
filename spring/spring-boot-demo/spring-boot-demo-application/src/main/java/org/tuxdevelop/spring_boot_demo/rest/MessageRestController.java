package org.tuxdevelop.spring_boot_demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.tuxdevelop.spring_boot_demo.service.MessageServiceBean;
import org.tuxdevelop.spring_boot_demo.service.dto.MessageDTO;
import org.tuxdevelop.spring_boot_demo.service.dto.MessageWrapperDTO;
import org.tuxdevelop.spring_boot_demo.service.intf.MessageService;

import java.util.List;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageRestController implements MessageService {

    @Autowired
    private MessageServiceBean messageServiceBean;

    @Override
    @RequestMapping(value = MessageService.SEND_MESSAGE_URI, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageDTO sendMessage(@RequestBody final MessageDTO messageDTO) {
        return messageServiceBean.sendMessage(messageDTO);
    }

    @Override
    @RequestMapping(value = MessageService.GET_RECEIVED_MESSAGES_URI, method = RequestMethod.GET)
    public MessageWrapperDTO getAllReceivedMessageDTOsByUserName(
            @RequestParam(value = "userName") final String userName) {
        final List<MessageDTO> messageDTOs = messageServiceBean.getAllReceivedMessagesByUserName(userName);
        final MessageWrapperDTO response = new MessageWrapperDTO();
        response.setMessageDTOs(messageDTOs);
        return response;
    }

    @Override
    @RequestMapping(value = MessageService.GET_SEND_MESSAGES_URI, method = RequestMethod.GET)
    public MessageWrapperDTO getAllSendMessageDTOsByUserName(@RequestParam(value = "userName") final String userName) {
        final List<MessageDTO> messageDTOs = messageServiceBean.getAllSendMessagesByUserName(userName);
        final MessageWrapperDTO response = new MessageWrapperDTO();
        response.setMessageDTOs(messageDTOs);
        return response;
    }
}
