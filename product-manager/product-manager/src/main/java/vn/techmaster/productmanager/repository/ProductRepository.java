package vn.techmaster.productmanager.repository;

import vn.techmaster.productmanager.entities.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
