package org.tuxdevelop.demos.spring_data_repositories_cdi.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "customer")
public class Customer extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Contact contact;
}
