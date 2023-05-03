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
package com.apuntesdejava.salesmanager.web.view.converter;

import com.apuntesdejava.salesmanager.api.CategoryService;
import com.apuntesdejava.salesmanager.model.Category;
import java.util.Optional;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author diego
 */
@Named
@FacesConverter(value = "categoryConverter", managed = true)
public class CategoryConverter implements Converter<Category> {

    @EJB
    private CategoryService service;

    @Override
    public Category getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        Optional<Category> category = Optional.ofNullable(service.findById(NumberUtils.toLong(value)));
        if (category.isEmpty()) {
            return null;
        }
        return category.get();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Category value) {
        return value == null ? null : String.valueOf(value.getId());
    }

}
