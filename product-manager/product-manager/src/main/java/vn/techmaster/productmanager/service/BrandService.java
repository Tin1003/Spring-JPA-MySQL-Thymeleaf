package vn.techmaster.productmanager.service;
import vn.techmaster.productmanager.entities.Brand;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrandService {

    Brand findById(long id);

    Brand save(Brand t);

    Brand update(long id, Brand t);

    void deleteById(long id);

    Page<Brand> findAll(Pageable pageable);
}
