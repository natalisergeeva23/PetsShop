package com.example.springmodels.models;

import javax.persistence.*;


import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "licence")
public class Licence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty(message = "Name Product is required")
    private String numberLicence;

    @OneToOne(mappedBy = "licence")
    private Client client;

    public Licence(long id, String numberLicence, Client client) {
        this.id = id;
        this.numberLicence = numberLicence;
        this.client = client;
    }

    public Licence() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumberLicence() {
        return numberLicence;
    }

    public void setNumberLicence(String numberLicence) {
        this.numberLicence = numberLicence;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
