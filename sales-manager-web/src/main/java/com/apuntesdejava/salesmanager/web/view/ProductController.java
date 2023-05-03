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

import com.apuntesdejava.salesmanager.api.ProductService;
import com.apuntesdejava.salesmanager.model.Category;
import com.apuntesdejava.salesmanager.model.Product;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author diego
 */
@Named
@SessionScoped
public class ProductController implements Serializable {

    @EJB
    private ProductService productService;

    private Category categorySelected;

    private Product currentProduct;

    public List<Product> getProductsList() {
        var products = productService.listByCategory(categorySelected);
        return products;
    }

    public Category getCategorySelected() {
        return categorySelected;
    }

    public void setCategorySelected(Category categorySelected) {
        this.categorySelected = categorySelected;
    }

    public Product getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(Product currentProduct) {
        this.currentProduct = currentProduct;
    }

    public String newProductAction() {
        currentProduct = new Product();
        currentProduct.setCategory(categorySelected);
        return "/products/form";
    }

    public String save() {
        if (currentProduct.getId() == 0) {
            productService.create(currentProduct);
        }
        return "/products/index";
    }

}
