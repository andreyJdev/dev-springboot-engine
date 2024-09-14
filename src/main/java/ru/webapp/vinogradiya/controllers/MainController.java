package ru.webapp.vinogradiya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.webapp.vinogradiya.services.ProductsService;
import ru.webapp.vinogradiya.services.SelectionsService;

@Controller
public class MainController {
    ProductsService productsService;
    SelectionsService selectionsService;

    @Autowired
    public MainController(ProductsService productsService, SelectionsService selectionsService) {
        this.productsService = productsService;
        this.selectionsService = selectionsService;
    }

    @GetMapping
    public String indexPage(Model model) {
        model.addAttribute("arrayOfImages", productsService.findAllByHaveImage());
        return "index";
    }
    @GetMapping("/price")
    public String pricePage(Model model) {
        model.addAttribute("products", productsService.findAllByNullSelection());
        model.addAttribute("selections", selectionsService.findAll());
        return "price";
    }
    @GetMapping("/payment")
    public String paymentPage() {
        return "payment";
    }
    @GetMapping("/contacts")
    public String contactsPage() {

        return "contacts";
    }
}
