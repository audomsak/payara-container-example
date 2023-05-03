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
package com.apuntesdejava.salesmanager.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Diego Silva <diego.silva at apuntesdejava.com>
 */
@Embeddable
public class MeasurementConvertId implements Serializable {
    
    @Column(name = "unit_source",length = 5)
    private String measurementUnitSource;
    
    @Column(name = "unit_target",length = 5)
    private String measurementUnitTarget;

    public MeasurementConvertId() {
    }

    public MeasurementConvertId(String measurementUnitSource, String measurementUnitTarget) {
        this.measurementUnitSource = measurementUnitSource;
        this.measurementUnitTarget = measurementUnitTarget;
    }

    public String getMeasurementUnitSource() {
        return measurementUnitSource;
    }

    public void setMeasurementUnitSource(String measurementUnitSource) {
        this.measurementUnitSource = measurementUnitSource;
    }

    public String getMeasurementUnitTarget() {
        return measurementUnitTarget;
    }

    public void setMeasurementUnitTarget(String measurementUnitTarget) {
        this.measurementUnitTarget = measurementUnitTarget;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.measurementUnitSource);
        hash = 17 * hash + Objects.hashCode(this.measurementUnitTarget);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MeasurementConvertId other = (MeasurementConvertId) obj;
        if (!Objects.equals(this.measurementUnitSource, other.measurementUnitSource)) {
            return false;
        }
        return Objects.equals(this.measurementUnitTarget, other.measurementUnitTarget);
    }
}
