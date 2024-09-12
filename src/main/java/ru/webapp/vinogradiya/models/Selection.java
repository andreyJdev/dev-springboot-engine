package ru.webapp.vinogradiya.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "Selection")
public class Selection {
    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = "selection_sequence",
            sequenceName = "selection_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Имя селекции не должно быть пустым")
    @NotNull(message = "Имя селекции не должно быть пустым")
    private String name;

    @OneToMany(mappedBy = "selection")
    private List<Product> products;
}
