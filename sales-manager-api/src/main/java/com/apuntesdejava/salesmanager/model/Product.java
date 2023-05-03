package com.apuntesdejava.salesmanager.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Diego Silva <diego.silva at apuntesdejava.com>
 */
@Entity
public class Product implements Serializable {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(length = 100)
    private String name;

    @Column(length = 200)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "measurement_id")
    private MeasurementUnit measurementUnit;

    @OneToMany(mappedBy = "product")
    private List<Stock> stocks;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public MeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = StringUtils.upperCase(name);
    }

    public void setDescription(String description) {
        this.description = StringUtils.upperCase(description);
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public double getTotalCount() {
        return stocks == null
                ? 0.0
                : stocks.stream().map(Stock::getCount).reduce(0.0, Double::sum);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Product other = (Product) obj;
        return this.id == other.id;
    }

}
