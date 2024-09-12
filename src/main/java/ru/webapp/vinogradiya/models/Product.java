package ru.webapp.vinogradiya.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "strength")
    private String strength;

    @Column(name = "cluster")
    private String cluster;

    @Column(name = "berry")
    private String berry;

    @Column(name = "taste")
    private String taste;

    @Column(name = "resistance_cold")
    private Integer resistanceCold;

    @Column(name = "price_seed")
    private Integer priceSeed;

    @Column(name = "price_cut")
    private Integer priceCut;

    @Column(name = "image")
    private String image;

    @Column(name = "selection_mini")
    private String selectionMini;

    @Column(name = "saj_in")
    private Integer sajIn;

    @Column(name = "saj_out")
    private Integer sajOut;

    @ManyToOne
    @JoinColumn(name = "selection_id", referencedColumnName = "id")
    Selection selection;
}
