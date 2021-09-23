package vn.techmaster.productmanager.controller;
import java.util.Optional;

import vn.techmaster.productmanager.entities.*;
import vn.techmaster.productmanager.service.*;
import vn.techmaster.productmanager.validation.*;

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

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private static final String DETAIL = "manager/category/detail";
    private static final String LIST = "manager/category/list";
    private static final String REDIRECT_CATEGORY = "redirect:/category";
    private final CategoryService categoryService;

    @GetMapping
    public String getList(@PageableDefault(5) Pageable pageable, Model model) {
        model.addAttribute("page", categoryService.findAll(pageable));
        return LIST;
    }

    @GetMapping(value = { "/{id}/edit", "/create" })
    public String getForm(@PathVariable Optional<Long> id, Model model) {
        Category category;
        if (id.isPresent()) {
            category = categoryService.findById(id.get());
        } else {
            category = new Category();
        }
        model.addAttribute("category", category);
        return DETAIL;
    }

    @PostMapping("/create")
    public String save(@Validated(CategoryGroup.class) Category category, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return DETAIL;
        }
        categoryService.save(category);
        return REDIRECT_CATEGORY;
    }

    @PostMapping("/{id}/edit")
    public String update(@Validated(CategoryGroup.class) Category category, BindingResult bindingResult,
            @PathVariable long id) {
        if (bindingResult.hasErrors()) {
            return DETAIL;
        }
        categoryService.update(id, category);
        return REDIRECT_CATEGORY;
    }

    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable long id) {
        categoryService.deleteById(id);
        return REDIRECT_CATEGORY;
    }
}
