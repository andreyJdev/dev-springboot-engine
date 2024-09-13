package ru.webapp.vinogradiya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.webapp.vinogradiya.models.Product;
import ru.webapp.vinogradiya.models.Selection;
import ru.webapp.vinogradiya.services.ProductsService;
import ru.webapp.vinogradiya.services.SelectionsService;

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
        model.addAttribute("products", productsService.findAll());
        return "admin/admin";
    }

    @GetMapping("/{id}/upd-product")
    public String showAllProducts(@PathVariable("id") Long id, Model model) {
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

    @PostMapping("/save-prod")
    public String saveNewProduct(@ModelAttribute("product") Product product) {
        productsService.create(product);
        return "redirect:/admin";
    }

    @GetMapping("/new-selection")
    public String newSelection(Model model) {
        model.addAttribute("selection", new Selection());
        return "admin/newSelection";
    }


    @PostMapping("/save-selec")
    public String saveNewSelection(@ModelAttribute("selection") Selection selection) {
        selectionsService.create(selection);
        return "redirect:/admin";
    }
}
