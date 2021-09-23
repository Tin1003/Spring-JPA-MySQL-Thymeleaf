package vn.techmaster.productmanager.service;

import vn.techmaster.productmanager.entities.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Product findById(long id);

    Product save(Product t);

    Product update(long id, Product t);

    void deleteById(long id);

    Page<Product> findAll(Pageable pageable);
}
