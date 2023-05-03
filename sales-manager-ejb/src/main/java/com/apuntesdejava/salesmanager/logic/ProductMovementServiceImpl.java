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

import com.apuntesdejava.salesmanager.api.ProductMovementService;
import com.apuntesdejava.salesmanager.model.Product;
import com.apuntesdejava.salesmanager.model.ProductMovement;
import com.apuntesdejava.salesmanager.model.Stock;
import com.apuntesdejava.salesmanager.model.Storehouse;
import com.apuntesdejava.salesmanager.model.type.ProductMovementType;
import com.apuntesdejava.salesmanager.repository.ProductMovementRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Diego Silva <diego.silva at apuntesdejava.com>
 */
@Stateless
public class ProductMovementServiceImpl extends AbstractService<Long, ProductMovement> implements ProductMovementService {

    private static final Logger LOGGER = Logger.getLogger(ProductMovementServiceImpl.class.getName());

    @Inject
    private StockService stockService;

    @Inject
    private ProductMovementRepository repository;

    @Override
    protected ProductMovementRepository getRepository() {
        return repository;
    }

    @Override
    public ProductMovement create(Product product, double count, ProductMovementType type, double unitPrice, Storehouse storeHouse, String comment) {
        ProductMovement movement = create(new ProductMovement(product, count, type, unitPrice, storeHouse, comment));
        Optional<Stock> stockOpt = stockService.findByProductPrice(product, unitPrice, storeHouse);
        if (stockOpt.isPresent()) {
            Stock stock = stockOpt.get();
            stock.setCount(stock.getCount() + (count * (type == ProductMovementType.INCOMING ? 1 : -1)));
            stock.setLastUpdate(LocalDateTime.now());
            stock.setPrice(unitPrice);
            stockService.update(stock.getId(), stock);
        } else {
            var stock = stockService.create(new Stock(product, storeHouse, count, unitPrice));
            LOGGER.log(Level.FINE, "stock id:{0}", stock.getId());
        }
        return movement;
    }

    @Override
    public List<ProductMovement> listByProduct(Product p) {
        return repository.listByProduct(p);
    }

}
