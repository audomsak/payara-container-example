/*
 * Copyright 2022 Diego Silva <diego.silva at apuntesdejava.com>.
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
package com.apuntesdejava.salesmanager.logic;

import com.apuntesdejava.salesmanager.api.CategoryService;
import com.apuntesdejava.salesmanager.model.Category;
import com.apuntesdejava.salesmanager.repository.CategoryRepository;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Diego Silva <diego.silva at apuntesdejava.com>
 */
@Stateless
public class CategoryServiceImpl extends AbstractService<Long, Category> implements CategoryService {

    @EJB
    private CategoryRepository repository;

    @Override
    protected CategoryRepository getRepository() {
        return repository;
    }

}
