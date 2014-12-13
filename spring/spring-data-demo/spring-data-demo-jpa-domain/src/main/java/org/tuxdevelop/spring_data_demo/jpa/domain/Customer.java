package org.tuxdevelop.spring_data_demo.jpa.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "customer")
public class Customer extends AbstractDomainEntity {

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Contact> contacts;

    public void merge(final Customer customer) {
        super.merge(customer);
    }

    /*
     * Domain Model business validation
     */

    public void validateAdd() {
        assert (contacts != null && !contacts.isEmpty());
        super.validateAdd();
    }

    public void validateUpdate() {
        super.validateUpdate();
        validateAdd();
    }

    public void addContactsToCustomer(final Collection<Contact> contacts) {
        if (contacts != null && !contacts.isEmpty()) {
            for (final Contact contact : contacts) {
                addContactToCustomer(contact);
            }
        }
    }

    public void addContactToCustomer(final Contact contact) {
        if (!hasStandardContact()) {
            if (contacts == null) {
                contacts = new LinkedList<>();
            }
            contact.setCustomer(this);
            contacts.add(contact);
        } else {
            throw new RuntimeException("Customer with id :" + getId() + " already has a standard contact!");
        }
    }

    private Boolean hasStandardContact() {
        Boolean hasStandardContact = Boolean.FALSE;
        if (this.contacts != null && !this.contacts.isEmpty()) {
            for (final Contact contact : contacts) {
                if (ContactClassifier.STANDARD.equals(contact.getContactClassifier())) {
                    hasStandardContact = Boolean.TRUE;
                    break;
                }
            }
        }
        return hasStandardContact;
    }
}
