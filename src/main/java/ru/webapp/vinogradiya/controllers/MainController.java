package ru.webapp.vinogradiya.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @GetMapping
    public String indexPage() {

        return "index";
    }
    @GetMapping("/price")
    public String pricePage() {

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
