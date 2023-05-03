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


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Diego Silva <diego.silva at apuntesdejava.com>
 */
@Embeddable
public class PersonId implements Serializable {

    @Column(name = "type_person", length = 5)
    private String typePersonId;

    @Column(length = 30, name = "document_number")
    private String documentNumber;

    public String getTypePersonId() {
        return typePersonId;
    }

    public void setTypePersonId(String typePersonId) {
        this.typePersonId = typePersonId;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.typePersonId);
        hash = 89 * hash + Objects.hashCode(this.documentNumber);
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
        final PersonId other = (PersonId) obj;
        if (!Objects.equals(this.typePersonId, other.typePersonId)) {
            return false;
        }
        return Objects.equals(this.documentNumber, other.documentNumber);
    }

}
