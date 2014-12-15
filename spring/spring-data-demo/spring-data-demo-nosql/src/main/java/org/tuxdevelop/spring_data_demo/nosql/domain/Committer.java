package org.tuxdevelop.spring_data_demo.nosql.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Committer {

    @Id
    private String id;

    private String name;

}
