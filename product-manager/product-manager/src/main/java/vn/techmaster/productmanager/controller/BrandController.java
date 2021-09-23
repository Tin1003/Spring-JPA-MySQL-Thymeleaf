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
@RequestMapping("/brand")
public class BrandController {

    private static final String DETAIL = "manager/brand/detail";
    private static final String LIST = "manager/brand/list";
    private static final String REDIRECT_BRAND = "redirect:/brand";
    private final BrandService brandService;
    private final CategoryService categoryService;

    @GetMapping
    public String getList(@PageableDefault(5) Pageable pageable, Model model) {
        model.addAttribute("page", brandService.findAll(pageable));
        return LIST;
    }

    @GetMapping(value = { "/{id}/edit", "/create" })
    public String getForm(@PathVariable Optional<Long> id, Model model) {
        Brand brand;
        if (id.isPresent()) {
            brand = brandService.findById(id.get());
        } else {
            brand = new Brand();
        }
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("brand", brand);
        return DETAIL;
    }

    @PostMapping({ "/create", "/{id}/edit" })
    public String save(@Validated(BrandGroup.class) Brand brand, BindingResult bindingResult,
            @PathVariable Optional<Long> id, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return DETAIL;
        }
        if (id.isPresent()) {
            brandService.update(id.get(), brand);
        } else {
            brandService.save(brand);
        }
        return REDIRECT_BRAND;
    }

    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable long id) {
        brandService.deleteById(id);
        return REDIRECT_BRAND;
    }
}
