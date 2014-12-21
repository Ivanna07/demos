package org.tuxdevelop.spring_boot_cxf_demo.util;

import org.tuxdevelop.spring_boot_cxf_demo.domain.AbstractDomainEntity;

import java.util.Date;

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
