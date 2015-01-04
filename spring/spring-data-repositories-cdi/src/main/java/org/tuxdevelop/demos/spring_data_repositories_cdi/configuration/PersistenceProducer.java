package org.tuxdevelop.demos.spring_data_repositories_cdi.configuration;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceProducer {

	@Produces
	@ApplicationScoped
	public EntityManagerFactory createEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("customerPU");
	}

	public void close(@Disposes final EntityManagerFactory entityManagerFactory) {
		entityManagerFactory.close();
	}

	@Produces
	@RequestScoped
	public EntityManager createEntityManager(final EntityManagerFactory entityManagerFactory) {
		return entityManagerFactory.createEntityManager();
	}

	public void close(@Disposes final EntityManager entityManager) {
		entityManager.close();
	}

}
