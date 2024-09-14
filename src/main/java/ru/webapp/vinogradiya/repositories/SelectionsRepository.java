package ru.webapp.vinogradiya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.webapp.vinogradiya.models.Selection;

import java.util.Optional;

@Repository
public interface SelectionsRepository extends JpaRepository<Selection, Long> {
    Optional<Selection> findByName(String name);
    boolean existsByName(String name);
}
