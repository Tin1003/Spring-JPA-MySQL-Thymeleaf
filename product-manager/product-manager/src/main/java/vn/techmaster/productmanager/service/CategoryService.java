package vn.techmaster.productmanager.service;

import java.util.List;

import vn.techmaster.productmanager.entities.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Category findById(long id);

    Category save(Category t);

    Category update(long id, Category t);

    void deleteById(long id);

    Page<Category> findAll(Pageable pageable);

    List<Category> findAll();
}
