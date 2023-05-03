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
package com.apuntesdejava.salesmanager.model;

import com.apuntesdejava.salesmanager.model.type.ProductMovementType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "product_movement")
public class ProductMovement implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "movement_id")
    private long id;

    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product;

    @Column(name = "movement_date")
    private LocalDateTime date;

    @Column(name = "count_")
    private double count;

    @Column(name = "type_movement")
    @Enumerated(value = EnumType.STRING)
    private ProductMovementType type;

    @Column(name = "unit_price")
    private double unitPrice;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @JoinColumn(name = "store_house_id")
    @ManyToOne
    private Storehouse storeHouse;

    public ProductMovement() {
    }

    public ProductMovement(Product product, double count, ProductMovementType type, double unitPrice, Storehouse storeHouse, String comment) {
        this.product = product;
        this.count = count;
        this.type = type;
        this.unitPrice = unitPrice;
        this.storeHouse = storeHouse;
        this.date = LocalDateTime.now();
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public ProductMovementType getType() {
        return type;
    }

    public void setType(ProductMovementType type) {
        this.type = type;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Storehouse getStoreHouse() {
        return storeHouse;
    }

    public void setStoreHouse(Storehouse storeHouse) {
        this.storeHouse = storeHouse;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final ProductMovement other = (ProductMovement) obj;
        return this.id == other.id;
    }

}
