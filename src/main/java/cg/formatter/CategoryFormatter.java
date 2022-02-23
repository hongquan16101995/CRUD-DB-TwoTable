package cg.formatter;

import cg.model.Category;
import cg.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.util.Locale;

public class CategoryFormatter implements Formatter<Category> {

    private final ICategoryService iCategoryService;

    @Autowired
    public CategoryFormatter(ICategoryService iCategoryService) {
        this.iCategoryService = iCategoryService;
    }

    @Override
    public Category parse(String text, Locale locale) {
        return iCategoryService.findById(Integer.parseInt(text));
    }

    @Override
    public String print(Category object, Locale locale) {
        return "[" + object.getC_id() + ", " +object.getC_name() + "]";
    }
}
