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
package com.apuntesdejava.salesmanager.web.view;

import com.apuntesdejava.salesmanager.api.StorehouseService;
import com.apuntesdejava.salesmanager.model.Storehouse;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author diego
 */
@Named
@SessionScoped
public class StorehouseController implements Serializable {

    @EJB
    private StorehouseService  storehouseService;

    private Storehouse storehouseSelected;

    public List<Storehouse> getStorehouseList() {
        return storehouseService.listAll();
    }

    public Storehouse getStorehouseSelected() {
        return storehouseSelected;
    }

    public void setStorehouseSelected(Storehouse storehouseSelected) {
        this.storehouseSelected = storehouseSelected;
    }

    public void storehouseNewCommand() {
        storehouseSelected = new Storehouse();
        PrimeFaces.current().dialog().openDynamic("/storehouses/form", Map.of("modal", true), null);
    }

    public void save() {
        if (storehouseSelected.getId() == 0) {
            var model = storehouseService.create(storehouseSelected);
            PrimeFaces.current().dialog().closeDynamic(model);
        } else {
            var model = storehouseService.update(storehouseSelected.getId(), storehouseSelected);
            PrimeFaces.current().dialog().closeDynamic(model);
        }
    }
}
