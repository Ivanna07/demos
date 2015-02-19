package org.tuxdevelop.spring_boot_demo.view.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.tuxdevelop.spring_boot_demo.service.dto.UserDTO;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RegisterModel {

    @Getter
    @Setter
    private UserDTO user;

}
