package org.tuxdevelop.spring_boot_demo.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "user_role")
public class UserRole extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "role")
	private String role;
	@Column(name = "user_name")
	private String userName;

}
