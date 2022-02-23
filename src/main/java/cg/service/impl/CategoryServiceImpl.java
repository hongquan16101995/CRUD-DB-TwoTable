package cg.service.impl;

import cg.model.Category;
import cg.repository.ICategoryRepository;
import cg.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public ArrayList<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public Category delete(int id) {
        return null;
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findById(id);
    }
}
