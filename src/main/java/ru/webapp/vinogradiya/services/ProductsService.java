package ru.webapp.vinogradiya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.webapp.vinogradiya.models.Product;
import ru.webapp.vinogradiya.repositories.ProductsRepository;
import ru.webapp.vinogradiya.utils.ProductsTableItemDTO;

import java.util.*;
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

    public Set<Product> findAllByNullSelection() {
        return new TreeSet<>(findAll().stream()
                .filter(product -> product.getSelection() == null)
                .toList());
    }
    // ищем все товары, у которых есть изображения и получаем массив (массивов по 3 товара)
    public List<List<Product>> findAllByHaveImage() {
        List<Product> allProductsWithImages = new ArrayList<>(
                findAll().stream()
                        .filter(product -> !product.getImage().isEmpty())
                        .sorted()
                        .collect(Collectors.toList())
        );
        return splitArrayToChunks(allProductsWithImages, 3);
    }
    // делим массив на подмассивы chunk размером chunkSize
    public List<List<Product>> splitArrayToChunks(List<Product> originalArray, Integer chunkSize) {
        List<List<Product>> chunkArray = new ArrayList<>();
        for (int i = 0; i < originalArray.size() - (chunkSize - 1); i += chunkSize) {
            List<Product> chunk = new ArrayList<>(originalArray.subList(i, i + chunkSize));
            chunkArray.add(chunk);
        }
        return chunkArray;
    }

    // поиск всех товаров для таблицы
    public Map<String, Set<ProductsTableItemDTO>> findProductsForTable() {
        // отсортированный сет продуктов
        Set<Product> sortedProducts = new TreeSet<>(productsRepository.findJoinAllProductsWithSelections());
        // отсортированный сет названий селекций
        Set<String> sortedSelections = new TreeSet<>(
                sortedProducts.stream()
                        .filter(product -> product.getSelection() != null)
                        .map(product -> product.getSelection().getName())
                        .toList());

        Map<String, Set<ProductsTableItemDTO>> productsDTO = new LinkedHashMap<>();

        // LinkedHashMap заполнение ключами (именами селекций)
        for (String selection : sortedSelections)
            productsDTO.put(selection, new LinkedHashSet<>());
        // последний ключ для null
        productsDTO.put("-", new LinkedHashSet<>());

        // наполнение LinkedHashMap продуктами
        for (Product product : sortedProducts) {
            if (product.getSelection() != null) {
                productsDTO.get(product.getSelection().getName()).add(new ProductsTableItemDTO(product));
            }
            else {
                productsDTO.get("-").add(new ProductsTableItemDTO(product));
            }
        }
        return productsDTO;
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
