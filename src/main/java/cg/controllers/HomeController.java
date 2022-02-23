package cg.controllers;

import cg.model.Category;
import cg.model.Product;
import cg.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import cg.service.IProductService;

import java.util.ArrayList;

@Controller
@RequestMapping("/product")
public class HomeController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

//    @ModelAttribute("categories")
//    public Iterable<Category> categories(){
//        return categoryService.findAll();
//    }

    @GetMapping
    public ModelAndView showProducts() {
        ModelAndView modelAndView = new ModelAndView("list");
        ArrayList<Product> products = productService.findAll();
        if (products.isEmpty()) {
            modelAndView.addObject("message", "No products!");
            modelAndView.addObject("color", "red");
        }
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("list");
        Product product = productService.delete(id);
        if (product == null) {
            modelAndView.addObject("message", "Id invalid!");
            modelAndView.addObject("color", "red");
        }
        ArrayList<Product> products = productService.findAll();
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView showDetail(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Product product = productService.findById(id);
        if (product != null) {
            modelAndView.addObject("product", product);
        } else {
            modelAndView.addObject("message", "Id invalid!");
        }
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createProduct() {
        ModelAndView modelAndView = new ModelAndView("create");
        ArrayList<Category> categories = categoryService.findAll();
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView create(@ModelAttribute Product product) {
        ModelAndView modelAndView = new ModelAndView("create");
        Product productCreate = productService.save(product);
        if (productCreate != null) {
            modelAndView.addObject("message", "Create successfully!");
        }
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editProduct(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Product product = productService.findById(id);
        if (product != null) {
            modelAndView.addObject("product", product);
        } else {
            modelAndView.addObject("message", "Id invalid!");
        }
        ArrayList<Category> categories = categoryService.findAll();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView edit(@ModelAttribute Product product, @PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        product.setId(id);
        Product productEdit = productService.save(product);
        if (productEdit != null) {
            modelAndView.addObject("message", "Update successfully!");
        }
        return modelAndView;
    }
}
