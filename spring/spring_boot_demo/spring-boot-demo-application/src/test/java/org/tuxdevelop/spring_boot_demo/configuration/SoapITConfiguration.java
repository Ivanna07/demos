package org.tuxdevelop.spring_boot_demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;
import org.tuxdevelop.spring_boot_demo.service.intf.MessageService;
import org.tuxdevelop.spring_boot_demo.service.intf.UserService;

@Configuration
public class SoapITConfiguration {

    final String USER_NAME = "root";
    final String PASSWORD = "root";
    final String SERVICE_URL = "http://localhost:8080/soap/";

    @Bean
    public UserService userServiceSoap(){
        final JaxWsPortProxyFactoryBean factoryBean = new JaxWsPortProxyFactoryBean();
        factoryBean.setServiceInterface(UserService.class);
        factoryBean.setUsername(USER_NAME);
        factoryBean.setPassword(PASSWORD);
        factoryBean.setEndpointAddress(SERVICE_URL + UserService.SOAP_SERVICE_NAME);
        factoryBean.setServiceName(UserService.SOAP_SERVICE_NAME);
        factoryBean.afterPropertiesSet();
        return (UserService) factoryBean.getObject();
    }

    @Bean
    public MessageService messageServiceSoap(){
        final JaxWsPortProxyFactoryBean factoryBean = new JaxWsPortProxyFactoryBean();
        factoryBean.setServiceInterface(MessageService.class);
        factoryBean.setUsername(USER_NAME);
        factoryBean.setPassword(PASSWORD);
        factoryBean.setEndpointAddress(SERVICE_URL + MessageService.SOAP_SERVICE_NAME);
        factoryBean.setServiceName(MessageService.SOAP_SERVICE_NAME);
        factoryBean.afterPropertiesSet();
        return (MessageService) factoryBean.getObject();
    }
}
