/*
 * Copyright 2022 Diego Silva <diego.silva at apuntesdejava.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.apuntesdejava.salesmanager.repository;

import com.apuntesdejava.salesmanager.model.Category;
import com.apuntesdejava.salesmanager.model.Product;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import static javax.persistence.criteria.JoinType.LEFT;
import javax.persistence.criteria.Root;

/**
 *
 * @author Diego Silva <diego.silva at apuntesdejava.com>
 */
@ApplicationScoped
public class ProductRepository extends AbstractRepository<Long, Product> {

    @PersistenceContext(unitName = "salesPU")
    private EntityManager em;

    public ProductRepository() {
        //super(Product.class, Product_.name.getName());
        super(Product.class, "name");
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Product> listByCategory(Optional< Category> category) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> product = query.from(Product.class);
        query.select(product)
                //                .orderBy(cb.asc(product.get(Product_.name)));
                .orderBy(cb.asc(product.get("name")));
        if (category.isPresent()) {
            query.where(
                    //                    cb.equal(product.get(Product_.category), category.get())
                    cb.equal(product.get("category"), category.get())
            );
        }
        /*product.fetch(Product_.category, LEFT);
        product.fetch(Product_.measurementUnit, LEFT);
        product.fetch(Product_.stocks, LEFT);*/
        product.fetch("category", LEFT);
        product.fetch("measurementUnit", LEFT);
        product.fetch("stocks", LEFT);
        return em.createQuery(query).getResultList();

    }

}
