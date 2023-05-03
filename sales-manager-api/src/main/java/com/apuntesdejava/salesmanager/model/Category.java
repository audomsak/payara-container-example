package com.apuntesdejava.salesmanager.model;

import java.io.Serializable;
import java.util.Locale;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Diego Silva <diego.silva at apuntesdejava.com>
 */
@Entity
public class Category implements Serializable {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(length = 100)
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = StringUtils.upperCase(name, Locale.getDefault());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Category other = (Category) obj;
        return this.id == other.id;
    }

}
