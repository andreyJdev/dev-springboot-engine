package ru.webapp.vinogradiya.models;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
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

    @Column(name = "time")
    private String time;

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

    @Column(name = "description")
    private String description;

    @Column(name = "selection_mini")
    private String selectionMini;

    @Column(name = "saj_in")
    private Integer sajIn;

    @Column(name = "saj_out")
    private Integer sajOut;

    @Column(name = "cher_in")
    private Integer cherIn;

    @Column(name = "cher_out")
    private Integer cherOut;

    @ManyToOne
    @JoinColumn(name = "selection_id", referencedColumnName = "id")
    Selection selection;

    public Product() {

    }

    public Product(String strength, String name, String time,
                   String cluster, String berry,
                   String taste, Integer resistanceCold,
                   Integer priceSeed, Integer priceCut,
                   String image, String description, String selectionMini,
                   Integer sajIn, Integer sajOut,
                   Integer cherIn, Integer cherOut,
                   Selection selection) {
        this.time = time;
        this.strength = strength;
        this.name = name;
        this.cluster = cluster;
        this.berry = berry;
        this.taste = taste;
        this.resistanceCold = resistanceCold;
        this.priceSeed = priceSeed;
        this.priceCut = priceCut;
        this.image = image;
        this.description = description;
        this.selectionMini = selectionMini;
        this.sajIn = sajIn;
        this.sajOut = sajOut;
        this.cherIn = cherIn;
        this.cherOut = cherOut;
        this.selection = selection;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getBerry() {
        return berry;
    }

    public void setBerry(String berry) {
        this.berry = berry;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public Integer getResistanceCold() {
        return resistanceCold;
    }

    public void setResistanceCold(Integer resistanceCold) {
        this.resistanceCold = resistanceCold;
    }

    public Integer getPriceSeed() {
        return priceSeed;
    }

    public void setPriceSeed(Integer priceSeed) {
        this.priceSeed = priceSeed;
    }

    public Integer getPriceCut() {
        return priceCut;
    }

    public void setPriceCut(Integer priceCut) {
        this.priceCut = priceCut;
    }

    public String getSelectionMini() {
        return selectionMini;
    }

    public void setSelectionMini(String selectionMini) {
        this.selectionMini = selectionMini;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSajIn() {
        return sajIn;
    }

    public void setSajIn(Integer sajIn) {
        this.sajIn = sajIn;
    }

    public Integer getSajOut() {
        return sajOut;
    }

    public void setSajOut(Integer sajOut) {
        this.sajOut = sajOut;
    }

    public Integer getCherIn() {
        return cherIn;
    }

    public void setCherIn(Integer cherIn) {
        this.cherIn = cherIn;
    }

    public Integer getCherOut() {
        return cherOut;
    }

    public void setCherOut(Integer cherOut) {
        this.cherOut = cherOut;
    }

    public Selection getSelection() {
        return selection;
    }

    public void setSelection(Selection selection) {
        this.selection = selection;
    }
}
