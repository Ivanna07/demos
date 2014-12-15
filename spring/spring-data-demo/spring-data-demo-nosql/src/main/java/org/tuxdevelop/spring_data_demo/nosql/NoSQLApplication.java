package org.tuxdevelop.spring_data_demo.nosql;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.tuxdevelop.spring_data_demo.nosql.domain.Committer;
import org.tuxdevelop.spring_data_demo.nosql.domain.Text;
import org.tuxdevelop.spring_data_demo.nosql.repository.CommitterRepository;
import org.tuxdevelop.spring_data_demo.nosql.repository.TextRepository;

@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = "org.tuxdevelop.spring_data_demo.nosql.repository")
@ComponentScan(basePackages = "org.tuxdevelop.spring_data_demo.nosql")
public class NoSQLApplication {

    public static void main(final String[] args){
        SpringApplication.run(NoSQLApplication.class,args);
    }

    @Bean
    public InitializingBean populateTestData(final TextRepository textRepository,final CommitterRepository committerRepository){
      return new InitializingBean() {
          @Override
          public void afterPropertiesSet() throws Exception {
            final Committer committer = new Committer();
            committer.setName("initialisingUser");
            committerRepository.save(committer);
            final Text text = new Text();
            text.setCommitter(committer);
            text.setText("some text to show");
            textRepository.save(text);
          }
      };
    }

}
