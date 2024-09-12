package ru.webapp.vinogradiya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.webapp.vinogradiya.models.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
}
