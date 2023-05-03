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
package com.apuntesdejava.salesmanager.logic;

import com.apuntesdejava.salesmanager.model.Product;
import com.apuntesdejava.salesmanager.model.Stock;
import com.apuntesdejava.salesmanager.model.Storehouse;
import com.apuntesdejava.salesmanager.repository.StockRepository;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Diego Silva <diego.silva at apuntesdejava.com>
 */
@Stateless
public class StockService extends AbstractService<Long, Stock> {

    @Inject
    private StockRepository repository;

    @Override
    protected StockRepository getRepository() {
        return repository;
    }

    public Optional<Stock> findByProductPrice(Product product, double unitPrice, Storehouse storeHouse) {
        return repository.findByProductPrice(product, unitPrice, storeHouse);
    }
}
