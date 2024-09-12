package ru.webapp.vinogradiya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.webapp.vinogradiya.models.Product;
import ru.webapp.vinogradiya.repositories.ProductsRepository;

@Service
public class ProductsService {
    ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public void create(Product newProduct) {
        productsRepository.save(newProduct);
    }
}
