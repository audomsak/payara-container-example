package com.apuntesdejava.salesmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;
import javax.persistence.Transient;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Diego Silva <diego.silva at apuntesdejava.com>
 */
@Entity
@Table(name = "measurement_unit")
public class MeasurementUnit implements Serializable {

    @Id
    @Column(length = 5, name = "measurement_id")
    private String measurementId;

    @Column(length = 20)
    private String name;

    @Transient
    private boolean newRecord;

    public String getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(String measurementId) {
        this.measurementId = StringUtils.upperCase(measurementId, Locale.getDefault());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = StringUtils.upperCase(name, Locale.getDefault());
    }

    public boolean isNewRecord() {
        return newRecord;
    }

    public void setNewRecord(boolean newRecord) {
        this.newRecord = newRecord;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.measurementId);
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
        final MeasurementUnit other = (MeasurementUnit) obj;
        return Objects.equals(this.measurementId, other.measurementId);
    }

}
