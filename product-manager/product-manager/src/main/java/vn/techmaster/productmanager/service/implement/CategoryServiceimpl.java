package vn.techmaster.productmanager.service.implement;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.techmaster.productmanager.exception.*;
import lombok.RequiredArgsConstructor;
import vn.techmaster.productmanager.entities.Category;
import vn.techmaster.productmanager.service.CategoryService;
import vn.techmaster.productmanager.repository.*;

@Service
@RequiredArgsConstructor
public class CategoryServiceimpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    @Override
    public Category findById(long id) {
        
        return categoryRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Category save(Category category) {
        
        return categoryRepository.save(category);
    }

    @Override
    public Category update(long id, Category category) {
        if(category.getId()== null || id != category.getId() ){
            throw new BadRequestException();
        }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(long id) {
        reqiredExsists(id);
        categoryRepository.deleteById(id);
        
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return categoryRepository.findAll(pageable);
    }

    @Override
    public List<Category> findAll() {
        // TODO Auto-generated method stub
        return categoryRepository.findAll();
    }
    private void reqiredExsists(long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException();
        }
    }
    
}
