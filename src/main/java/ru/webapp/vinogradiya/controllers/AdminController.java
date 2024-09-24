package ru.webapp.vinogradiya.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.webapp.vinogradiya.models.Product;
import ru.webapp.vinogradiya.models.Selection;
import ru.webapp.vinogradiya.services.ProductsService;
import ru.webapp.vinogradiya.services.SelectionsService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    ProductsService productsService;
    SelectionsService selectionsService;

    @Autowired
    public AdminController(ProductsService productsService, SelectionsService selectionsService) {
        this.productsService = productsService;
        this.selectionsService = selectionsService;
    }

    @GetMapping()
    public String admin(Model model) {
        List<Product> products = productsService.findAll();
        products = products.stream().sorted().toList();
        int maxSizePreview = 64;
        for (Product product : products) {
            if (product.getDescription().length() > maxSizePreview) {
                product.setDescription(product.getDescription().substring(0, maxSizePreview) + "...");
            }
        }
        model.addAttribute("products", products);
        return "admin/admin";
    }

    @GetMapping("/{id}/upd-product")
    public String showProductForUpdate(@PathVariable("id") Long id, Model model) {
        model.addAttribute("selections", selectionsService.findAll());
        model.addAttribute("product", productsService.findById(id));
        return "admin/updProduct";
    }

    @PostMapping("/{id}")
    public String updProduct(@PathVariable("id") Long id,
                             @ModelAttribute("product") Product product) {
        productsService.update(id, product);
        return "redirect:/admin";
    }

    @GetMapping("/new-product")
    public String newProduct(Model model) {
        model.addAttribute("selections", selectionsService.findAll());
        model.addAttribute("product", new Product());
        return "admin/newProduct";
    }

    @PostMapping("/save-product")
    public String saveNewProduct(@ModelAttribute("product") @Valid Product product,
                                 BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "admin/newProduct";
        productsService.create(product);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/del-product")
    public String deleteProduct(@PathVariable("id") Long id) {
        productsService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/new-selection")
    public String newSelection(Model model) {
        model.addAttribute("selection", new Selection());
        model.addAttribute("selections", selectionsService.findAll());
        model.addAttribute("status", "Добавить");
        return "admin/newUpdSelection";
    }


    @PostMapping("/save-selection")
    public String saveNewSelection(@ModelAttribute("selection") @Valid Selection selection,
                                   BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("selection", new Selection());
            model.addAttribute("selections", selectionsService.findAll());
            model.addAttribute("status", "Добавить");
            return "admin/newUpdSelection";
        }
        selectionsService.create(selection);
        return "redirect:/admin/new-selection";
    }

    @GetMapping("/{id}/upd-selection")
    public String showSelectionForUpdate(@PathVariable("id") Long id, Model model) {
        model.addAttribute("selection", selectionsService.findById(id));
        model.addAttribute("selections", selectionsService.findAll());
        model.addAttribute("status", "Изменить");
        return "admin/newUpdSelection";
    }

    @PostMapping("/{id}/upd")
    public String updSelection(@PathVariable("id") Long id,
                               @ModelAttribute("selection") @Valid Selection selection,
                               BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("selection", selectionsService.findById(id));
            model.addAttribute("selections", selectionsService.findAll());
            model.addAttribute("status", "Изменить");
            return "admin/newUpdSelection";
        }
        selectionsService.update(id, selection);

        return "redirect:/admin/new-selection";
    }

    @PostMapping("/{id}/del-selection")
    public String delSelection(@PathVariable("id") Long id) {
        selectionsService.delete(id);
        return "redirect:/admin/new-selection";
    }
}
