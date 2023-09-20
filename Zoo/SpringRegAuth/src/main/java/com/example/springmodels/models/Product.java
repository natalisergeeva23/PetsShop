package com.example.springmodels.models;

import javax.persistence.*;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty(message = "Name Product is required")
    private String nameProduct;
    @PositiveOrZero(message = "Product Count should be a positive number or zero")
    private int countProduct;
    @PositiveOrZero(message = "Product Price should be a positive number or zero")
    private int priceProduct;

    @ManyToOne
    @JoinColumn(name = "type_product_id")
    private TypeProduct typeProduct;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "product")
    private List<Zakaz> zakazes;

    @ManyToMany
    @JoinTable (name="client_product",
            joinColumns=@JoinColumn (name="product_id"),
            inverseJoinColumns=@JoinColumn(name="client_id"))
    private List<Client> clients;

    public Product(long id, String nameProduct, int countProduct, int priceProduct, TypeProduct typeProduct, Store store) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.countProduct = countProduct;
        this.priceProduct = priceProduct;
        this.typeProduct = typeProduct;
        this.store = store;
    }

    public Product() {

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(int countProduct) {
        this.countProduct = countProduct;
    }

    public int getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(int priceProduct) {
        this.priceProduct = priceProduct;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public TypeProduct getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProduct typeProduct) {
        this.typeProduct = typeProduct;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<Zakaz> getZakazes() {
        return zakazes;
    }

    public void setZakazes(List<Zakaz> zakazes) {
        this.zakazes = zakazes;
    }
}
