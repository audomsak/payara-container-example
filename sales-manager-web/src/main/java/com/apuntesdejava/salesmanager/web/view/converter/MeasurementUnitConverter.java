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

import com.apuntesdejava.salesmanager.api.MeasurementUnitService;
import com.apuntesdejava.salesmanager.model.MeasurementUnit;
import java.util.Optional;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author diego
 */
@Named
@FacesConverter(value = "measurementUnitConverter", managed = true)
public class MeasurementUnitConverter implements Converter<MeasurementUnit> {

    @EJB
    private MeasurementUnitService service;

    @Override
    public MeasurementUnit getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        Optional<MeasurementUnit> measurement = Optional.ofNullable(service.findById(value));
        if (measurement.isEmpty()) {
            return null;
        }
        return measurement.get();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, MeasurementUnit value) {
        return value == null ? null : value.getMeasurementId();
    }

}
