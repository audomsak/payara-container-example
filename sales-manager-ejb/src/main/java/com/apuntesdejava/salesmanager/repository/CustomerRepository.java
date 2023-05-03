package com.apuntesdejava.salesmanager.repository;

import com.apuntesdejava.salesmanager.model.CustomerPerson;
import com.apuntesdejava.salesmanager.model.PersonId;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class CustomerRepository extends AbstractRepository<PersonId, CustomerPerson> {

    @PersistenceContext(unitName = "salesPU")
    private EntityManager em;

    public CustomerRepository(){
        super(CustomerPerson.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
