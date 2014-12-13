package org.tuxdevelop.spring_data_demo.util;

import java.util.Date;

import org.tuxdevelop.spring_data_demo.jpa.domain.AbstractDomainEntity;

public class GenericFactory {

	public static <T extends AbstractDomainEntity> T attachGenericValues(final T entity) {
		entity.setChangedAt(new Date());
		entity.setChangedBy("jUnitTest");
		entity.setInsertedAt(new Date());
		entity.setInsertedBy("jUnitTests");
		entity.setValid(1);
		return entity;
	}

}
