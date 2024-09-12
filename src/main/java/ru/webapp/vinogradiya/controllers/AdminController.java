package ru.webapp.vinogradiya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.webapp.vinogradiya.models.Product;
import ru.webapp.vinogradiya.models.Selection;
import ru.webapp.vinogradiya.services.ProductsService;
import ru.webapp.vinogradiya.services.SelectionsService;

@Controller
@RequestMapping("admin")
public class AdminController {
    ProductsService productsService;
    SelectionsService selectionsService;

    @Autowired
    public AdminController(SelectionsService selectionsService) {
        this.productsService = productsService;
        this.selectionsService = selectionsService;
    }

    @GetMapping()
    public String admin() {

        return "admin/admin";
    }

    @GetMapping("/new-product")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "admin/newProduct";
    }

    @GetMapping("/new-selection")
    public String newSelection(Model model) {
        model.addAttribute("selection", new Selection());
        return "admin/newSelection";
    }

    @PostMapping()
    public String saveNewSelection(@ModelAttribute("selection") Selection selection) {
        selectionsService.create(selection);
        return "redirect:/admin";
    }
}
