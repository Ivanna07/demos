package org.tuxdevelop.spring_data_demo.rest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.tuxdevelop.spring_data_demo.jpa.domain.*;
import org.tuxdevelop.spring_data_demo.jpa.util.CommunicationFactory;
import org.tuxdevelop.spring_data_demo.jpa.util.ContactFactory;
import org.tuxdevelop.spring_data_demo.jpa.util.CustomerFactory;
import org.tuxdevelop.spring_data_demo.rest.repository.CommunicationRepository;
import org.tuxdevelop.spring_data_demo.rest.repository.ContactRepository;
import org.tuxdevelop.spring_data_demo.rest.repository.CustomerRepository;

import java.util.Collection;
import java.util.LinkedList;

@EnableAutoConfiguration
@EntityScan(value = "org.tuxdevelop.spring_data_demo.jpa.domain")
@ComponentScan(basePackages = "org.tuxdevelop.spring_data_demo.rest")
public class SpringDataRestApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SpringDataRestApplication.class, args);
    }

    @Bean
    public InitializingBean populateStaticDate(final CustomerRepository customerRepository, final ContactRepository
            contactRepository, final CommunicationRepository communicationRepository) {
        return new InitializingBean() {

            @Override
            public void afterPropertiesSet() throws Exception {
                final Communication emailCommunication = CommunicationFactory.createEmailCommunication(
                        CommunicationClassifier.STANDARD);
                communicationRepository.save(emailCommunication);
                final Communication phoneCommunication = CommunicationFactory.createPhoneCommunication(
                        CommunicationClassifier.STANDARD);
                communicationRepository.save(phoneCommunication);
                final Contact contact = ContactFactory.createContact(ContactClassifier.STANDARD);
                contactRepository.save(contact);
                emailCommunication.setContact(contact);
                phoneCommunication.setContact(contact);
                communicationRepository.save(emailCommunication);
                communicationRepository.save(phoneCommunication);
                Collection<Communication> emailCommunications = new LinkedList<>();
                Collection<Communication> phoneCommunications = new LinkedList<>();
                emailCommunications.add(emailCommunication);
                phoneCommunications.add(phoneCommunication);
                contact.setEmailCommunications(emailCommunications);
                contact.setPhoneCommunications(phoneCommunications);
                contactRepository.save(contact);
                final Customer customer = CustomerFactory.createCustomer();
                customerRepository.save(customer);
                contact.setCustomer(customer);
                contactRepository.save(contact);
                final Collection<Contact> contacts = new LinkedList<>();
                contacts.add(contact);
                customer.setContacts(contacts);
                customerRepository.save(customer);
            }
        };
    }
}
