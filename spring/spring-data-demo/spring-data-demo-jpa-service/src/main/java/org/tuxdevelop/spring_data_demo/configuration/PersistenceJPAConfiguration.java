package org.tuxdevelop.spring_data_demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "org.tuxdevelop.spring_data_demo")
@EnableJpaRepositories(basePackages = "org.tuxdevelop.spring_data_demo.jpa.repository")
public class PersistenceJPAConfiguration {

	@Bean
	public DataSource dataSource() {
		final EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
		return embeddedDatabaseBuilder.setType(EmbeddedDatabaseType.H2).build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPackagesToScan("org.tuxdevelop.spring_data_demo.jpa.domain");
		final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
		entityManagerFactoryBean.setJpaProperties(additionalProperties());
		entityManagerFactoryBean.setPersistenceUnitName("customerPU");
		return entityManagerFactoryBean;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties additionalProperties() {
		final Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		return properties;
	}
}
