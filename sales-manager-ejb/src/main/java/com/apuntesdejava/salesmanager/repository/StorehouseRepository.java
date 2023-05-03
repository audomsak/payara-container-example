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

import com.apuntesdejava.salesmanager.model.Storehouse;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Diego Silva <diego.silva at apuntesdejava.com>
 */
@ApplicationScoped
public class StorehouseRepository extends AbstractRepository<Long, Storehouse> {

    @PersistenceContext(unitName = "salesPU")
    private EntityManager em;

    public StorehouseRepository() {
        super(Storehouse.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
