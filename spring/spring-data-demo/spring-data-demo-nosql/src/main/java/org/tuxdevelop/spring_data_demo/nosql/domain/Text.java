package org.tuxdevelop.spring_data_demo.nosql.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class Text {

    @Id
    private String id;

    private String text;

    @DBRef(lazy = false)
    private Committer committer;

}
