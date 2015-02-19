package org.tuxdevelop.demos.spring_data_repositories_cdi.configuration;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CdiConfig {

    @Produces
    @RequestScoped
    @PersistenceContext(unitName = "customerPU")
    public EntityManager entityManager;

}
