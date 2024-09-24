package ru.webapp.vinogradiya.utils;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import ru.webapp.vinogradiya.models.Product;
import ru.webapp.vinogradiya.models.Selection;

import java.util.Optional;

// модель с полями карточки одного товара
public class ProductCardDTO {
    private String name;
    private String description;
    private String image;

    private String time;

    private String strength;

    private String cluster;

    private String berry;

    private String taste;

    private String resistanceCold;

    private String priceSeed;

    private String priceCut;

    private String selection;

    private String selectionMini;

    public ProductCardDTO(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.image = product.getImage();
        this.time = product.getTime();
        this.strength = product.getStrength();
        this.cluster = product.getCluster();
        this.berry = product.getBerry();
        this.taste = product.getTaste();
        this.resistanceCold = product.getResistanceCold();
        this.priceSeed = product.getPriceSeed();
        this.priceCut = product.getPriceCut();
        Optional<Selection> selection = Optional.ofNullable(product.getSelection());
        this.selection = selection.map(Selection::getName).orElse("-");
        this.selectionMini = product.getSelectionMini();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getSelectionMini() {
        return selectionMini;
    }

    public void setSelectionMini(String selectionMini) {
        this.selectionMini = selectionMini;
    }
}
