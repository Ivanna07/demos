package org.tuxdevelop.spring_data_demo.nosql.controller.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.tuxdevelop.spring_data_demo.nosql.domain.Text;
import org.tuxdevelop.spring_data_demo.nosql.repository.TextRepository;

import java.util.Collection;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TextViewModel {

    @Autowired
    private TextRepository textRepository;

    private Collection<Text> textCollection;

    public Collection<Text> getTextCollection() {
        return textRepository.findAll();
    }

}
