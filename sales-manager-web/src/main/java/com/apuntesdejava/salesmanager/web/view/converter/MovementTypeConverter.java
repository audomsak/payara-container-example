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

import com.apuntesdejava.salesmanager.model.type.ProductMovementType;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author diego
 */
@Named
@FacesConverter(value = "movementTypeConverter", managed = true)
public class MovementTypeConverter implements Converter<ProductMovementType> {

    @Override
    public ProductMovementType getAsObject(FacesContext context, UIComponent component, String value) {
        return ProductMovementType.valueOf(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, ProductMovementType value) {
        return value.name();
    }

}
