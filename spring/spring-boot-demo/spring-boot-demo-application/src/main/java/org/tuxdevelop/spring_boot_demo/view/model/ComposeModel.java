package org.tuxdevelop.spring_boot_demo.view.model;

import lombok.Getter;
import lombok.Setter;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ComposeModel {

	@Getter
	@Setter
	private String emailAddress;
	@Getter
	@Setter
	private String messageText;

}
