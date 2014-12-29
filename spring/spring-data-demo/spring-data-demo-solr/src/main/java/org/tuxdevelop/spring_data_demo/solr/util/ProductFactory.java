package org.tuxdevelop.spring_data_demo.solr.util;

import org.jfairy.Fairy;
import org.jfairy.producer.DateProducer;
import org.jfairy.producer.person.Person;
import org.jfairy.producer.text.TextProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tuxdevelop.spring_data_demo.solr.domain.Product;
import org.tuxdevelop.spring_data_demo.solr.repository.ProductRepository;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Component
public class ProductFactory {

    private final Fairy fairy = Fairy.create();
    private final Person person = fairy.person();
    private final TextProducer textProducer = fairy.textProducer();
    private final DateProducer dateProducer = fairy.dateProducer();

    @Autowired
    private ProductRepository productRepository;

    public List<Product> createProducts(final Long count){
        final List<Product> products = new LinkedList<>();
        for(long i = 0;i<count;i++){
            products.add(createProduct());
        }
        return products;
    }

    public Product createProduct() {
        final Product product = new Product();
        product.setId(createId());
        product.setAuthor(createFullName());
        product.setCat(createWord());
        product.setCategory(createWord());
        product.setComments(createSentence());
        product.setDescription(createSentence());
        product.setInStock(createBoolean());
        product.setKeywords(createWord());
        product.setLastModified(createDate());
        product.setManufacture(createWord());
        product.setName(createWord());
        product.setPopularity(createInt());
        product.setPrice(createDouble());
        product.setResourceName(createWord());
        product.setSubject(createWord());
        product.setUrl(createWord());
        product.setWeight(createDouble());
        product.setSku(createWord());
        return product;
    }

    private Double createDouble(){
        return fairy.baseProducer().randomBetween(0, Double.MAX_VALUE);
    }

    private Integer createInt(){
        return fairy.baseProducer().randomInt(Integer.MAX_VALUE);
    }

    private Date createDate() {
        return dateProducer.randomDateInThePast(1).toDate();
    }

    private String createSentence() {
        return textProducer.sentence();
    }

    private String createWord() {
        return textProducer.word(1);
    }

    private String createId() {
        final long currentCount = productRepository.count();
        return new Long(currentCount + 1).toString();
    }

    private String createFullName() {
        return person.fullName();
    }

    private Boolean createBoolean() {
        return fairy.baseProducer().trueOrFalse();
    }
}
