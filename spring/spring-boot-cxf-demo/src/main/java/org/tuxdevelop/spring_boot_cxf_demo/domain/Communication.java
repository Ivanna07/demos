package org.tuxdevelop.spring_boot_cxf_demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = {"contact"})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "communication")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "communication_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Communication extends AbstractDomainEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "communication_type", insertable = false, updatable = false)
    private String communicationType;

    @Column(name = "communication_classifier")
    @Enumerated(EnumType.STRING)
    private CommunicationClassifier communicationClassifier;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Contact contact;

    protected void merge(final Communication communication) {
        communicationClassifier = communication.getCommunicationClassifier();
        super.merge(communication);
    }

    protected void validateAdd() {
        assert (communicationType != null);
        assert (communicationClassifier != null);
        super.validateAdd();
    }

    protected void validateUpdate() {
        super.validateUpdate();
        validateAdd();
    }
}
