package com.apuntesdejava.salesmanager.api;

import com.apuntesdejava.salesmanager.model.Category;
import java.util.List;
import java.util.Set;
import javax.ejb.Remote;

/**
 *
 * @author Diego Silva <diego.silva at apuntesdejava.com>
 */
@Remote
public interface CategoryService {

    List<Category> listAll();

    Category create(Category model);

    Set<Category> create(Category... model);

    boolean deleteById(Long key);

    Category findById(Long key);

    Category update(Long key, Category model);
}
