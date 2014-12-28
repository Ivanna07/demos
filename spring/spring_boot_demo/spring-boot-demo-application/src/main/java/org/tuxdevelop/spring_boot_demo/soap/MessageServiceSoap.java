package org.tuxdevelop.spring_boot_demo.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tuxdevelop.spring_boot_demo.service.MessageServiceBean;
import org.tuxdevelop.spring_boot_demo.service.dto.MessageDTO;
import org.tuxdevelop.spring_boot_demo.service.dto.MessageWrapperDTO;
import org.tuxdevelop.spring_boot_demo.service.intf.MessageService;

import java.util.Collection;

@Service
public class MessageServiceSoap implements MessageService{

    @Autowired
    private MessageServiceBean messageServiceBean;

    @Override
    public MessageDTO sendMessage(final MessageDTO messageDTO) {
        return messageServiceBean.sendMessage(messageDTO);
    }

    @Override
    public MessageWrapperDTO getAllReceivedMessageDTOsByUserName(final String userName) {
        final Collection<MessageDTO> messageDTOs = messageServiceBean.getAllReceivedMessagesByUserName(userName);
        final MessageWrapperDTO messageWrapperDTO = new MessageWrapperDTO();
        messageWrapperDTO.setMessageDTOs(messageDTOs);
        return messageWrapperDTO;
    }

    @Override
    public MessageWrapperDTO getAllSendMessageDTOsByUserName(final String userName) {
        final Collection<MessageDTO> messageDTOs = messageServiceBean.getAllSendMessagesByUserName(userName);
        final MessageWrapperDTO messageWrapperDTO = new MessageWrapperDTO();
        messageWrapperDTO.setMessageDTOs(messageDTOs);
        return messageWrapperDTO;
    }
}
