package vn.techmaster.productmanager.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.techmaster.productmanager.entities.Product;
import vn.techmaster.productmanager.service.ProductService;
import vn.techmaster.productmanager.exception.*;
import vn.techmaster.productmanager.repository.*;


@Service
@RequiredArgsConstructor
public class ProductServiceimpl implements ProductService{
    private final ProductRepository productRepository;
    
    
    @Override
    public Product findById(long id) {
        // TODO Auto-generated method stub
        return productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Product save(Product product) {
        // TODO Auto-generated method stub
        return productRepository.save(product);
    }

    @Override
    public Product update(long id, Product product) {
        if(product.getId()== null || id != product.getId()){
            throw new BadRequestException();
        }
        reqiredExsists(id);
        return productRepository.save(product);
    }

    @Override
    public void deleteById(long id) {
        // TODO Auto-generated method stub
        reqiredExsists(id);
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        
        return productRepository.findAll(pageable);
    }
    private void reqiredExsists(long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException();
        }
    }
    
}
