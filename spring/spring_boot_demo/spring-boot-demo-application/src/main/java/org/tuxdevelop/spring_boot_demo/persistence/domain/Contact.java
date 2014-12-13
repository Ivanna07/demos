package org.tuxdevelop.spring_boot_demo.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.tuxdevelop.spring_boot_demo.service.dto.ContactDTO;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "contact")
public class Contact extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "zip_code")
	private String zipCode;
	@Column(name = "city")
	private String city;
	@Column(name = "street_line")
	private String streetLine;

	public ContactDTO toDTO() {
		final ContactDTO dto = new ContactDTO();
		dto.setContactId(super.getId());
		dto.setCity(city);
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setStreetLine(streetLine);
		dto.setZipCode(zipCode);
		return dto;
	}

	public static Contact buildContact(final String firstName, final String lastName, final String zipCode,
			final String city, final String streetLine) {
		final Contact contact = new Contact();
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setCity(city);
		contact.setZipCode(zipCode);
		contact.setStreetLine(streetLine);
		return contact;
	}

}
