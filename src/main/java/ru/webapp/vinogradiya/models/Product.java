package ru.webapp.vinogradiya.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "product")
public class Product implements Comparable<Product> {
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

    @Column(name = "name", unique = true)
    //@UniqueName(message = "Выберите другое Название сорта, это занято")
    @NotBlank(message = "Обязательное заполнение Названия сорта")
    @Size(max = 100, message = "Не больше 100 символов")
    private String name;

    @Column(name = "time")
    @NotNull(message = "Поле не должно быть пустым")
    @NotEmpty(message = "Заполните Время созревания")
    @Size(max = 100, message = "Не больше 100 символов")
    private String time;

    @Column(name = "strength")
    @NotNull(message = "Поле не должно быть пустым")
    @NotEmpty(message = "Заполните Сила роста")
    @Size(max = 100, message = "Не больше 100 символов")
    private String strength;

    @Column(name = "cluster")
    @NotNull(message = "Поле не должно быть пустым")
    @NotEmpty(message = "Заполните Гроздь")
    @Size(max = 100, message = "Не больше 100 символов")
    private String cluster;

    @Column(name = "berry")
    @NotNull(message = "Поле не должно быть пустым")
    @NotEmpty(message = "Заполните Ягода")
    @Size(max = 100, message = "Не больше 100 символов")
    private String berry;

    @Column(name = "taste")
    @NotNull(message = "Поле не должно быть пустым")
    @NotEmpty(message = "Заполните Вкус и консистенция мякоти")
    @Size(max = 100, message = "Не больше 100 символов")
    private String taste;

    @Column(name = "resistance_cold")
    @NotNull(message = "Поле не должно быть пустым")
    @NotEmpty(message = "Заполните Морозостойкость")
    @Size(max = 100, message = "Не больше 100 символов")
    private String resistanceCold;

    @Column(name = "price_seed")
    @NotNull(message = "Поле не должно быть пустым")
    @NotEmpty(message = "Заполните Цена саженца")
    @Size(max = 100, message = "Не больше 100 символов")
    private String priceSeed;

    @Column(name = "price_cut")
    @NotNull(message = "Поле не должно быть пустым")
    @NotEmpty(message = "Заполните Цена черенка")
    @Size(max = 100, message = "Не больше 100 символов")
    private String priceCut;

    @Column(name = "image")
    @Size(max = 100, message = "Не больше 100 символов")
    private String image;

    @Column(name = "description")
    @Size(max = 2048, message = "Не больше 2048 символов")
    private String description;

    @Column(name = "selection_mini")
    @Size(max = 100, message = "Не больше 100 символов")
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
                   String taste, String resistanceCold,
                   String priceSeed, String priceCut,
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

    public String getResistanceCold() {
        return resistanceCold;
    }

    public void setResistanceCold(String resistanceCold) {
        this.resistanceCold = resistanceCold;
    }

    public String getPriceSeed() {
        return priceSeed;
    }

    public void setPriceSeed(String priceSeed) {
        this.priceSeed = priceSeed;
    }

    public String getPriceCut() {
        return priceCut;
    }

    public void setPriceCut(String priceCut) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name)
                && Objects.equals(time, product.time) && Objects.equals(strength, product.strength)
                && Objects.equals(cluster, product.cluster) && Objects.equals(berry, product.berry)
                && Objects.equals(taste, product.taste) && Objects.equals(resistanceCold, product.resistanceCold)
                && Objects.equals(priceSeed, product.priceSeed) && Objects.equals(priceCut, product.priceCut)
                && Objects.equals(image, product.image) && Objects.equals(description, product.description)
                && Objects.equals(selectionMini, product.selectionMini) && Objects.equals(sajIn, product.sajIn)
                && Objects.equals(sajOut, product.sajOut) && Objects.equals(cherIn, product.cherIn)
                && Objects.equals(cherOut, product.cherOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, time, strength, cluster, berry, taste, resistanceCold,
                priceSeed, priceCut, image, description, selectionMini, sajIn, sajOut, cherIn, cherOut);
    }

    @Override
    public int compareTo(Product product) {
        return this.name.compareTo(product.getName());
    }
}
