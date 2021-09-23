package vn.techmaster.productmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.techmaster.productmanager.entities.*;


public interface BrandRepository extends JpaRepository<Brand, Long> {
    
}
