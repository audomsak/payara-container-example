/*
 * Copyright 2021 diego.
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
package com.apuntesdejava.salesmanager.web.view;

import com.apuntesdejava.salesmanager.api.ProductMovementService;
import com.apuntesdejava.salesmanager.api.ProductService;
import com.apuntesdejava.salesmanager.model.Product;
import com.apuntesdejava.salesmanager.model.ProductMovement;
import com.apuntesdejava.salesmanager.model.type.ProductMovementType;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.primefaces.PrimeFaces;

/**
 *
 * @author diego
 */
@Named
@SessionScoped
public class ProductMovementController implements Serializable {

    private static final Logger LOG = Logger.getLogger(ProductMovementController.class.getName());

    @EJB
    private ProductMovementService productMovementService;
    @EJB
    private ProductService productService;
    private ProductMovement currentMovement;
    private Product currentProduct;

    public ProductMovement getCurrentMovement() {
        return currentMovement;
    }

    public void setCurrentMovement(ProductMovement currentMovement) {
        this.currentMovement = currentMovement;
    }

    public List<ProductMovement> getMovementsProductList() {
        return productMovementService.listByProduct(currentProduct);
    }

    public Product getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(Product currentProduct) {
        this.currentProduct = currentProduct;
    }

    public void addMovementAction() {
        LOG.log(Level.INFO, "-----> Product assign for movement:{0}", this.currentMovement);
        currentMovement = new ProductMovement();
        currentMovement.setProduct(currentProduct);
        PrimeFaces.current().dialog().openDynamic("/products/movement-form", Map.of("modal", true), null);
    }

    public void saveMovementAction() {
        currentMovement.setDate(LocalDateTime.now());
        var model = productMovementService.create(
                currentProduct,
                currentMovement.getCount(),
                currentMovement.getType(),
                currentMovement.getUnitPrice(),
                currentMovement.getStoreHouse(),
                currentMovement.getComment()
        );
        PrimeFaces.current().dialog().closeDynamic(model);
    }

    public List<ProductMovementType> getMovementTypes() {
        return Arrays.asList(ProductMovementType.values());
    }

    public String showMovements() {
        LOG.info("show movements");
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String productId = params.get("productId");
        if (StringUtils.isNotBlank(productId)) {
            Optional<Product> product = Optional.ofNullable(productService.findById(NumberUtils.toLong(productId)));
            if (product.isPresent()) {
                this.currentProduct = product.get();
                return "/products/movements";
            }
        }
        return null;
    }

}
