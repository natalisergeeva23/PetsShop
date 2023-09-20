package com.example.springmodels.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;
    @NotEmpty(message = "First Name is required")
    private String firstNameClient;
    @NotEmpty(message = "Second Name is required")
    private String secondNameClient;
    @NotEmpty(message = "Middle Name is required")
    private String middleNameClient;
    @NotEmpty(message = "Client Address is required")
    private String clientAddress;


    @ManyToMany
    @JoinTable (name="product_client",
            joinColumns=@JoinColumn (name="client_id"),
            inverseJoinColumns=@JoinColumn(name="product_id"))
    private List<Product> products;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "licence_id")
    private Licence licence;

    @OneToMany(mappedBy = "client")
    private List<Zakaz> zakazes;

    public Client() {

    }

    public Client(int id, String firstNameClient, String secondNameClient, String middleNameClient, String clientAddress, Licence licence) {
        this.id = id;
        this.firstNameClient = firstNameClient;
        this.secondNameClient = secondNameClient;
        this.middleNameClient = middleNameClient;
        this.clientAddress = clientAddress;
        this.licence = licence;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFirstNameClient() {
        return firstNameClient;
    }

    public void setFirstNameClient(String firstNameClient) {
        this.firstNameClient = firstNameClient;
    }

    public String getSecondNameClient() {
        return secondNameClient;
    }

    public void setSecondNameClient(String secondNameClient) {
        this.secondNameClient = secondNameClient;
    }

    public String getMiddleNameClient() {
        return middleNameClient;
    }

    public void setMiddleNameClient(String middleNameClient) {
        this.middleNameClient = middleNameClient;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Licence getLicence() {
        return licence;
    }

    public void setLicence(Licence licence) {
        this.licence = licence;
    }

    public List<Zakaz> getZakazes() {
        return zakazes;
    }

    public void setZakazes(List<Zakaz> zakazes) {
        this.zakazes = zakazes;
    }
}

