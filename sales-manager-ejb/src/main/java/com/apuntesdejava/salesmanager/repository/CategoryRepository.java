package com.apuntesdejava.salesmanager.repository;

import com.apuntesdejava.salesmanager.model.Category;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class CategoryRepository extends AbstractRepository<Long, Category> {

    @PersistenceContext(unitName = "salesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoryRepository() {
        super(Category.class);
    }
}
