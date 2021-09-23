package vn.techmaster.productmanager.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.techmaster.productmanager.service.BrandService;
import vn.techmaster.productmanager.entities.Brand;
import vn.techmaster.productmanager.repository.*;
import vn.techmaster.productmanager.exception.*;

@Service
@RequiredArgsConstructor
public class BrandServiceimpl implements BrandService {
    private final BrandRepository brandRepository;

    @Override
    public Brand findById(long id) {
        
        return brandRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Brand save(Brand brand) {
        
        return brandRepository.save(brand);
    }

    @Override
    public Brand update(long id, Brand brand) {
        if (brand.getId() == null || id != brand.getId()) {
            throw new BadRequestException();
        }
        reqiredExsists(id);
        return brandRepository.save(brand);
    }

    @Override
    public void deleteById(long id) {
       reqiredExsists(id);
        brandRepository.deleteById(id);
    }

    @Override
    public Page<Brand> findAll(Pageable pageable) {

        return brandRepository.findAll(pageable);
    }
    private void reqiredExsists(long id) {
        if (!brandRepository.existsById(id)) {
            throw new ResourceNotFoundException();
        }
    }
    
}
