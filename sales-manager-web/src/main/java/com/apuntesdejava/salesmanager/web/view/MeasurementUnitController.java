package com.apuntesdejava.salesmanager.web.view;

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
import com.apuntesdejava.salesmanager.api.MeasurementUnitService;
import com.apuntesdejava.salesmanager.model.MeasurementUnit;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author diego
 */
@Named
@SessionScoped
public class MeasurementUnitController implements Serializable {

    @EJB
    private MeasurementUnitService service;

    private MeasurementUnit currentUm;

    public List<MeasurementUnit> getMeasurementUnitList() {
        return service.listAll();
    }

    public MeasurementUnit getCurrentUm() {
        return currentUm;
    }

    public void setCurrentUm(MeasurementUnit currentUm) {
        this.currentUm = currentUm;
    }

    public String newMeasurementUnit() {
        currentUm = new MeasurementUnit();
        currentUm.setNewRecord(true);
        return "/measurement-unit/form";
    }

    public String save() {
        if (currentUm.isNewRecord()) {
            service.create(currentUm);
        } else {
            service.update(currentUm.getMeasurementId(), currentUm);
        }
        return "/measurement-unit/index";
    }

    public String edit() {
        String id = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");
        currentUm = service.findById(id);
        return "/measurement-unit/form";
    }

    public String delete() {
        String id = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");
        service.deleteById(id);
        return "/measurement-unit/index";
    }

}
