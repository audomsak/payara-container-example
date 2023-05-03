package com.apuntesdejava.salesmanager.repository;

import com.apuntesdejava.salesmanager.model.MeasurementUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class MeasurementUnitRepository extends AbstractRepository<String, MeasurementUnit> {

    @PersistenceContext(unitName = "salesPU")
    private EntityManager em;

    public MeasurementUnitRepository() {
        //super(MeasurementUnit.class, MeasurementUnit_.name.getName());
        super(MeasurementUnit.class, "name");
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
