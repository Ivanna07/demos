package org.tuxdevelop.demos.spring_data_repositories_cdi.service;

import org.tuxdevelop.demos.spring_data_repositories_cdi.domain.Customer;
import org.tuxdevelop.demos.spring_data_repositories_cdi.repository.CustomerRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;

@ApplicationScoped
@Path("/customers")
public class CustomerServiceBean implements CustomerService {

    @Inject
    private CustomerRepository customerRepository;

    @Override
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Customer addCustomer(final Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    @GET
    @Produces("application/json")
    @Path(value = "/{id}")
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Customer getCustomer(@PathParam(value = "id") final Long id) {
        return customerRepository.findOne(id);
    }
}
