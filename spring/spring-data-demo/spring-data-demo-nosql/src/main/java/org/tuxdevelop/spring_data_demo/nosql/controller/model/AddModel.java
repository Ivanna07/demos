package org.tuxdevelop.spring_data_demo.nosql.controller.model;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Data
@Scope(value = "request",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AddModel {

    private String committer;
    private String text;
}
