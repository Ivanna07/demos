package org.tuxdevelop.spring_boot_cxf_demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true,exclude = {"customer"})
@Entity
@Table(name = "contact")
public class Contact extends AbstractDomainEntity {

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Communication> emailCommunications;
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Communication> phoneCommunications;
    @Column(name = "contact_classifier")
    @Enumerated(EnumType.STRING)
    private ContactClassifier contactClassifier;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "street_line")
    private String streetLine;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "city")
    private String city;
    @ManyToOne
    @PrimaryKeyJoinColumn
    private Customer customer;

    public void merge(final Contact contact) {
        super.merge(contact);
        contactClassifier = contact.getContactClassifier();
        firstName = contact.getFirstName();
        lastName = contact.getLastName();
        streetLine = contact.getStreetLine();
        zipCode = contact.getZipCode();
        city = contact.getCity();
    }

  /*
   * Domain Model business validation
   */

    public void validateAdd() {
        assert (contactClassifier != null);
        assert (firstName != null);
        assert (lastName != null);
        assert (streetLine != null);
        assert (zipCode != null);
        assert (city != null);
        super.validateAdd();
    }

    public void validateUpdate() {
        super.validateUpdate();
        validateAdd();
    }

    public void addCommunicationsToContact(final Collection<Communication> communications) {
        if (communications != null && !communications.isEmpty()) {
            for (final Communication communication : communications) {
                addCommunicationToContact(communication);
                communication.setContact(this);
            }
        }
    }


    public void addCommunicationToContact(final Communication communication) {
        final String communicationType = communication.getCommunicationType();
        switch (communicationType) {
            case CommunicationType.EMAIL: {
                if (!hasStandardContact(communicationType)) {
                    if (emailCommunications == null) emailCommunications = new LinkedList<>();
                    emailCommunications.add(communication);
                }
                break;
            }
            case CommunicationType.PHONE: {
                if (!hasStandardContact(communicationType)) {
                    if (phoneCommunications == null) {
                        phoneCommunications = new LinkedList<>();
                    }
                    phoneCommunications.add(communication);
                }
                break;
            }
            default:
                throw new IllegalArgumentException("Unknown communication type ");
        }
    }

    private Boolean hasStandardContact(final String communicationType) {
        Boolean hasStandardContact = Boolean.FALSE;
        switch (communicationType) {
            case CommunicationType.EMAIL:
                if (this.emailCommunications != null && !this.emailCommunications.isEmpty()) {
                    for (final Communication communication : emailCommunications) {
                        if (CommunicationClassifier.STANDARD.equals(communication.getCommunicationClassifier())) {
                            hasStandardContact = Boolean.TRUE;
                            break;
                        }
                    }
                }
                break;
            case CommunicationType.PHONE:
                if (this.phoneCommunications != null && !this.phoneCommunications.isEmpty()) {
                    for (final Communication communication : phoneCommunications) {
                        if (CommunicationClassifier.STANDARD.equals(communication.getCommunicationClassifier())) {
                            hasStandardContact = Boolean.TRUE;
                            break;
                        }
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown communication type :" + communicationType);
        }
        return hasStandardContact;
    }

}
