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
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Diego Silva <diego.silva at apuntesdejava.com>
 */
@Embeddable
public class PersonAddress implements Serializable {

    @Column(name = "address_type", length = 15)
    private String addressType;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "city")
    private String city;

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = StringUtils.upperCase(addressType);
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = StringUtils.upperCase(streetAddress);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = StringUtils.upperCase(city);
    }

}
