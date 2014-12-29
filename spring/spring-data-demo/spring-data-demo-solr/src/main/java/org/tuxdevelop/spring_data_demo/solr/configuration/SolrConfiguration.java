package org.tuxdevelop.spring_data_demo.solr.configuration;

import org.apache.solr.client.solrj.SolrServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Configuration
@EnableSolrRepositories(basePackages = "org.tuxdevelop.spring_data_demo.solr.repository")
public class SolrConfiguration {

    @Bean
    public SolrServer solrServerEmbedded() throws IOException, SAXException, ParserConfigurationException {
        final EmbeddedSolrServerFactory embeddedSolrServerFactory = new EmbeddedSolrServerFactory("classpath:org" +
                "/tuxdevelop/spring_data_demo/solr/config");
        return embeddedSolrServerFactory.getSolrServer("example");
    }

    @Bean
    public SolrTemplate solrTemplate() throws ParserConfigurationException, SAXException, IOException {
        return new SolrTemplate(solrServerEmbedded());
    }
}
