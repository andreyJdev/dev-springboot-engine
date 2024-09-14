package ru.webapp.vinogradiya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.webapp.vinogradiya.models.Product;
import ru.webapp.vinogradiya.repositories.ProductsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ProductsService {
    ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> foundProduct = productsRepository.findById(id);
        return foundProduct.orElse(null);
    }

    public List<Product> findAllByNullSelection() {
        return findAll().stream()
                .filter(product -> product.getSelection() == null)
                .collect(Collectors.toList());
    }

    public static List<ArrayList<Product>> splitArrayList(ArrayList<Product> originalList, int chunkSize) {
        List<ArrayList<Product>> arrayOfArrays = new ArrayList<>();
        for (int i = 0; i + chunkSize <= originalList.size(); i += chunkSize) {
            ArrayList<Product> chunk = new ArrayList<>(originalList.subList(i, i + chunkSize));
            arrayOfArrays.add(chunk);
        }
        return arrayOfArrays;
    }

    public List<ArrayList<Product>> findAllByHaveImage() {
        ArrayList<Product> allHasImages = new ArrayList<>(findAll().stream()
                .filter(product -> !product.getImage().isEmpty())
                .collect(Collectors.toList()));
        ArrayList<ArrayList<Product>> arrayOfArrays = new ArrayList<>(splitArrayList(allHasImages, 3));
        return arrayOfArrays;
    }

    @Transactional
    public void create(Product newProduct) {
        productsRepository.save(newProduct);
    }

    @Transactional
    public void update(Long id, Product updatedProduct) {
        updatedProduct.setId(id);
        productsRepository.save(updatedProduct);
    }

    @Transactional
    public void delete(Long id) {
        productsRepository.deleteById(id);
    }
}
