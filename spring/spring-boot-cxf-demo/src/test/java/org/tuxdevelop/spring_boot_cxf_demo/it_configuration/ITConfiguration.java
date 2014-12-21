package org.tuxdevelop.spring_boot_cxf_demo.it_configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;
import org.tuxdevelop.spring_boot_cxf_demo.service.CommunicationService;
import org.tuxdevelop.spring_boot_cxf_demo.service.CustomerService;

@Configuration
public class ITConfiguration {

    private static final String SERVER_URI = "http://localhost:9001/soap/";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";

    @Bean
    public CustomerService customerServiceBean() {
        final JaxWsPortProxyFactoryBean factoryBean = new JaxWsPortProxyFactoryBean();
        factoryBean.setServiceInterface(CustomerService.class);
        factoryBean.setEndpointAddress(SERVER_URI + CustomerService.SERVICE_NAME);
        factoryBean.setUsername(USER_NAME);
        factoryBean.setPassword(PASSWORD);
        factoryBean.setServiceName(CustomerService.SERVICE_NAME);
        factoryBean.afterPropertiesSet();
        return (CustomerService) factoryBean.getObject();
    }

    @Bean
    public CommunicationService communicationServiceBean() {
        final JaxWsPortProxyFactoryBean factoryBean = new JaxWsPortProxyFactoryBean();
        factoryBean.setServiceInterface(CommunicationService.class);
        factoryBean.setEndpointAddress(SERVER_URI + CommunicationService.SERVICE_NAME);
        factoryBean.setUsername(USER_NAME);
        factoryBean.setPassword(PASSWORD);
        factoryBean.setServiceName(CommunicationService.SERVICE_NAME);
        factoryBean.afterPropertiesSet();
        return (CommunicationService) factoryBean.getObject();
    }

}
