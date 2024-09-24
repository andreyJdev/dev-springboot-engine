package ru.webapp.vinogradiya.utils;

import ru.webapp.vinogradiya.models.Product;

// Модель с полями таблицы для пользователя
public class ProductsTableItemDTO implements Comparable<ProductsTableItemDTO> {
    private String name;
    private String time;
    private String strength;
    private String cluster;
    private String berry;
    private String taste;
    private String resistanceCold;
    private String priceSeed;
    private String priceCut;
    private String selectionMini;

    public ProductsTableItemDTO(Product product) {
        this.name = product.getName();
        this.time = product.getTime();
        this.strength = product.getStrength();
        this.cluster = product.getCluster();
        this.berry = product.getBerry();
        this.taste = product.getTaste();
        this.resistanceCold = product.getResistanceCold();
        this.priceSeed = product.getPriceSeed();
        this.priceCut = product.getPriceCut();
        this.selectionMini = product.getSelectionMini();
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

    @Override
    public int compareTo(ProductsTableItemDTO product) {
        return this.name.compareTo(product.getName());
    }
}
