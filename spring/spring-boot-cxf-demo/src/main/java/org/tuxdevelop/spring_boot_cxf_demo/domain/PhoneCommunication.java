package org.tuxdevelop.spring_boot_cxf_demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@DiscriminatorValue(value = CommunicationType.PHONE)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PhoneCommunication extends Communication {

	private static final long serialVersionUID = 1L;

	@Column(name = "country_code")
	private String countryCode;
	@Column(name = "areas_code")
	private String areaCode;
	@Column(name = "number")
	private String number;

	public void merge(final PhoneCommunication communication) {
		countryCode = communication.getCountryCode();
		areaCode = communication.getAreaCode();
		number = communication.getNumber();
		super.merge(communication);
	}

    public void validateAdd(){
        assert(countryCode != null);
        assert (areaCode != null);
        assert (number != null);
        super.validateAdd();
    }

    public void validateUpdate(){
        super.validateUpdate();
        validateAdd();
    }
}
