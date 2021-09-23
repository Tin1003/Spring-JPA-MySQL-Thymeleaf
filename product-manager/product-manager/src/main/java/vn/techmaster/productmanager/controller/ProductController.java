package vn.techmaster.productmanager.controller;

import java.util.Optional;

import vn.techmaster.productmanager.entities.*;
import vn.techmaster.productmanager.service.*;

import vn.techmaster.productmanager.validation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/product")
public class ProductController {

    private static final String DETAIL = "manager/product/detail";
    private static final String LIST = "manager/product/list";
    private static final String REDIRECT_PRODUCT = "redirect:/product";

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public String getList(@PageableDefault(5) Pageable pageable, Model model) {
        Page<Product> findAll = productService.findAll(pageable);
        model.addAttribute("page", findAll);
        return LIST;
    }

    @GetMapping(value = { "{id}/edit", "/create" })
    public String getForm(@PathVariable Optional<Long> id, Model model,
            @RequestParam(defaultValue = "-1") int numOfDetail) {
        Product product;
        if (id.isPresent()) {
            product = productService.findById(id.get());
        } else {
            product = new Product();
        }
        if (numOfDetail == -1) {
            if (product.getProductDetails() == null) {
                numOfDetail = 0;
            } else {
                numOfDetail = product.getProductDetails().size() - 1;
            }
        }

        model.addAttribute("size", numOfDetail);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("product", product);
        return DETAIL;
    }

    @PostMapping(value = { "/create", "/{id}/edit" })
    public String save(@Validated(ProductGroup.class) Product product, BindingResult bindingResult,
            @PathVariable Optional<Long> id, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("size", product.getProductDetails().size() - 1);
            model.addAttribute("categories", categoryService.findAll());
            return DETAIL;
        }

        if (id.isPresent()) {
            productService.update(id.get(), product);
        } else {
            productService.save(product);
        }
        return REDIRECT_PRODUCT;
    }

    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable long id) {
        productService.deleteById(id);
        return REDIRECT_PRODUCT;
    }

}
