package org.tuxdevelop.spring_data_demo.solr.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.solr.client.solrj.beans.Field;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Field("id")
    private String id;
    @Field("sku")
    private String sku;
    @Field("name")
    private String name;
    @Field("manu")
    private String manufacture;
    @Field("cat")
    private String cat;
    @Field("weight")
    private Double weight;
    @Field("price")
    private Double price;
    @Field("popularity")
    private Integer popularity;
    @Field("inStock")
    private Boolean inStock;
    @Field("store")
    private String store;
    @Field("subject")
    private String subject;
    @Field("description")
    private String description;
    @Field("comments")
    private String comments;
    @Field("author")
    private String author;
    @Field("keywords")
    private String keywords;
    @Field("category")
    private String category;
    @Field("resourcename")
    private String resourceName;
    @Field("url")
    private String url;
    @Field("last_modified")
    private Date lastModified;
}
