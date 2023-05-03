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

import com.apuntesdejava.salesmanager.model.Product;
import com.apuntesdejava.salesmanager.model.Stock;
import com.apuntesdejava.salesmanager.model.Storehouse;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Diego Silva <diego.silva at apuntesdejava.com>
 */
@ApplicationScoped
public class StockRepository extends AbstractRepository<Long, Stock> {

    @PersistenceContext(unitName = "salesPU")
    private EntityManager em;

    public StockRepository() {
        super(Stock.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Optional<Stock> findByProductPrice(Product p, double price, Storehouse storeHouse) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Stock> cq = cb.createQuery(Stock.class);
        Root<Stock> stock = cq.from(Stock.class);
        cq.select(stock).where(
                cb.and(
                        cb.equal(stock.get("product"), p),
                        cb.equal(stock.get("storehouse"), storeHouse),
                        cb.equal(stock.get("price"), price)
                /*cb.equal(stock.get(Stock_.product), p),
                        cb.equal(stock.get(Stock_.storehouse), storeHouse),
                        cb.equal(stock.get(Stock_.price), price)*/
                )
        );
        return em.createQuery(cq).getResultStream().findFirst();
    }

}
