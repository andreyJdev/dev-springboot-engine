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
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "selection_sequence")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Имя селекции не должно быть пустым")
    @NotNull(message = "Имя селекции не должно быть пустым")
    private String name;

    @OneToMany(mappedBy = "selection")
    private List<Product> products;

    public Selection() {

    }

    public Selection(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
