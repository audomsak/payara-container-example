package com.apuntesdejava.salesmanager.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Diego Silva <diego.silva at apuntesdejava.com>
 */
@Entity
@Table(name = "measurement_convert")
public class MeasurementConvert implements Serializable {

    @EmbeddedId
    private MeasurementConvertId id;

    @ManyToOne
    @JoinColumn(
            name = "unit_source",
            referencedColumnName = "measurement_id",
            insertable = false,
            updatable = false
    )
    private MeasurementUnit source;

    @ManyToOne
    @JoinColumn(
            name = "unit_target",
            referencedColumnName = "measurement_id",
            insertable = false,
            updatable = false
    )
    private MeasurementUnit target;

    @Column(name = "convert_value")
    private double convert;

    public MeasurementConvertId getId() {
        return id;
    }

    public void setId(MeasurementConvertId id) {
        this.id = id;
    }

    public MeasurementUnit getSource() {
        return source;
    }

    public void setSource(MeasurementUnit source) {
        this.source = source;
    }

    public MeasurementUnit getTarget() {
        return target;
    }

    public void setTarget(MeasurementUnit target) {
        this.target = target;
    }

    public double getConvert() {
        return convert;
    }

    public void setConvert(double convert) {
        this.convert = convert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MeasurementConvert that = (MeasurementConvert) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
