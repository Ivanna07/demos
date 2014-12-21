package org.tuxdevelop.spring_boot_cxf_demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="email_communication")
public class EmailCommunication extends AbstractDomainEntity {

	private static final long serialVersionUID = 1L;

    @Column(name = "communication_type", insertable = false, updatable = false)
    private String communicationType;

    @Column(name = "communication_classifier")
    @Enumerated(EnumType.STRING)
    private CommunicationClassifier communicationClassifier;

    @ManyToOne
    @PrimaryKeyJoinColumn
    @XmlTransient
    private Contact contact;

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
