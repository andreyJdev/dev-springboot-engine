package ru.webapp.vinogradiya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.webapp.vinogradiya.models.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    boolean existsByName(String name);

    @Query("SELECT P FROM Product P LEFT JOIN FETCH P.selection")
    Set<Product> findJoinAllProductsWithSelections();
}
