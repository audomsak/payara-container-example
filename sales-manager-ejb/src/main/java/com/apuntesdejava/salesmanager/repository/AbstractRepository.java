package com.apuntesdejava.salesmanager.repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractRepository<K, E> {

    private final Class<E> clazz;
    private List<String> orderBy;

    protected AbstractRepository(Class<E> clazz) {
        this.clazz = clazz;
    }

    protected AbstractRepository(Class<E> clazz, String... fields) {
        this(clazz);
        if (fields != null && fields.length > 0) {
            orderBy = Arrays.asList(fields);
        }
    }

    protected abstract EntityManager getEntityManager();

    public E findById(K id) {
        return getEntityManager().find(clazz, id);
    }

    @Transactional
    public E persist(E model) {
        getEntityManager().persist(model);
        return model;
    }

    @Transactional
    public Set<E> persist(E... models) {
        return Arrays.asList(models)
                .stream()
                .map(model -> {
                    getEntityManager().persist(model);
                    return model;
                }).collect(Collectors.toSet());
    }

    @Transactional
    public boolean deleteById(K id) {
        E model = findById(id);
        if (model != null) {
            getEntityManager().remove(model);
            return true;
        }
        return false;
    }

    public List<E> listAll() {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(clazz);
        Root<E> model = cq.from(clazz);
        cq.select(model);
        if (orderBy != null && !orderBy.isEmpty()) {
            cq.orderBy(orderBy
                    .stream()
                    .map(field -> cb.asc(model.get(field)))
                    .collect(Collectors.toList())
            );
        }
        return em.createQuery(cq).getResultList();
    }

    @Transactional
    public E update(K key, E model) {
        EntityManager em = getEntityManager();
        E current = em.find(clazz, key);
        if (current != null) {
            return em.merge(model);
        }
        return null;

    }
}
