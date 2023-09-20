package com.example.springmodels.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "typeProduct")
public class TypeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty(message = "Name Type Product is required")
    private String nameTypeProduct;

    @OneToMany(mappedBy = "typeProduct")
    private List<Product> products;

    public TypeProduct(long id, String nameTypeProduct) {
        this.id = id;
        this.nameTypeProduct = nameTypeProduct;
    }

    public TypeProduct() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameTypeProduct() {
        return nameTypeProduct;
    }

    public void setNameTypeProduct(String nameTypeProduct) {
        this.nameTypeProduct = nameTypeProduct;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
