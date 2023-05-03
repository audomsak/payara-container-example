/*
 * Copyright 2021 Diego Silva <diego.silva at apuntesdejava.com>.
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

import com.apuntesdejava.salesmanager.api.CategoryService;
import com.apuntesdejava.salesmanager.model.Category;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.ejb.EJB;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Diego Silva <diego.silva at apuntesdejava.com>
 */
@Named(value = "categoryController")
@SessionScoped
public class CategoryController implements Serializable {

    @EJB
    private CategoryService service;

    private Category categoryCurrent;

    private List<Category> categoriesSelected;

    /**
     * Creates a new instance of CategoryController
     */
    public CategoryController() {
    }

    public List<Category> getCategoriesSelected() {
        return categoriesSelected;
    }

    public void setCategoriesSelected(List<Category> categoriesSelected) {
        this.categoriesSelected = categoriesSelected;
    }

    public List<Category> getCategoriesList() {
        return service.listAll();
    }

    public Category getCategoryCurrent() {
        return categoryCurrent;
    }

    public void setCategoryCurrent(Category categoryCurrent) {
        this.categoryCurrent = categoryCurrent;
    }

    public void categoryNewCommand() {
        categoryCurrent = new Category();
        PrimeFaces.current().dialog().openDynamic("/categories/form", Map.of("modal", true), null);
    }

    public void categoryDeleteCommand(Long id) {
        service.deleteById(id);
    }

    public void categoryEditCommand(Long id) {
        Optional<Category> opt = Optional.ofNullable(service.findById(id));
        if (opt.isPresent()) {
            categoryCurrent = opt.get();
            PrimeFaces.current().dialog().openDynamic("/categories/form", Map.of("modal", true), null);
        }
    }

    public void save() {
        if (categoryCurrent.getId() == 0) {
            var model = service.create(categoryCurrent);
            PrimeFaces.current().dialog().closeDynamic(model);
        } else {
            var model = service.update(categoryCurrent.getId(), categoryCurrent);
            PrimeFaces.current().dialog().closeDynamic(model);

        }

    }

}
