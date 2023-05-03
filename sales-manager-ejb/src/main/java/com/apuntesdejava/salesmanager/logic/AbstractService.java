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

import com.apuntesdejava.salesmanager.repository.AbstractRepository;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Diego Silva <diego.silva at apuntesdejava.com>
 * @param <K>
 * @param <E>
 */
public abstract class AbstractService<K, E> {

    protected abstract AbstractRepository<K, E> getRepository();

    public List<E> listAll() {
        return getRepository().listAll();
    }

    public E create(E model) {
        return getRepository().persist(model);
    }

    public Set<E> create(E... model) {
        return getRepository().persist(model);
    }

    public boolean deleteById(K key) {
        return getRepository().deleteById(key);
    }

    public E findById(K key) {
        return getRepository().findById(key);
    }

    public E update(K key, E model) {
        return getRepository().update(key, model);
    }

}
