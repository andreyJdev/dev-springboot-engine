package ru.webapp.vinogradiya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.webapp.vinogradiya.services.ProductsService;
import ru.webapp.vinogradiya.utils.ProductThunbnailDTO;
import ru.webapp.vinogradiya.utils.ProductsTableItemDTO;
import ru.webapp.vinogradiya.utils.ProductCardDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/products")
public class RestMainController {

    private final ProductsService productsService;

    @Autowired
    public RestMainController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("{productId:\\d}")
    public ProductCardDTO getProductInfo(@PathVariable("productId") Long productId) {
        ProductCardDTO productCard = new ProductCardDTO(
                productsService.findById(productId)
        );
        return productCard;
    }

    @GetMapping()
    public Map<String, Set<ProductsTableItemDTO>> getProductTableInfo() {
        return productsService.findProductsForTable();
    }

    @GetMapping("thunbnails")
    public List<List<ProductThunbnailDTO>> showThunbnailInfo() {
        return productsService.findAllByHaveImage();
    }


}
