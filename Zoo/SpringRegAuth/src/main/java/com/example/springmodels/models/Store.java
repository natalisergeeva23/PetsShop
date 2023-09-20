package com.example.springmodels.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty(message = "Name Store is required")
    private String nameStore;
    @NotEmpty(message = "Store Address is required")
    private String address;

    @OneToMany(mappedBy = "store")
    private List<Product> products;

    public Store(long id, String nameStore, String address) {
        this.id = id;
        this.nameStore = nameStore;
        this.address = address;
    }

    public Store() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
