package org.tuxdevelop.spring_data_demo.jpa.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@DiscriminatorValue(value = CommunicationType.EMAIL)
public class EmailCommunication extends Communication {

	private static final long serialVersionUID = 1L;

	@Column(name = "email_address")
	private String emailAddress;

	public void merge(final EmailCommunication communication) {
		emailAddress = communication.getEmailAddress();
		super.merge(communication);
	}

    public void validateAdd(){
        assert(emailAddress != null);
        super.validateAdd();
    }

    public void validateUpdate(){
        assert (getId() != null);
        super.validateUpdate();
        validateAdd();
    }
}
