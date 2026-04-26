package com.wincore.saasgestiondestock.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "Produit")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class Product extends AbstractEntity{

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "reference", nullable = false, unique = true)
    private String reference;

    @Column(name = "description")
    private String description;

    @Column(name = "seuil_alert", nullable = false)
    private String alertThreshold;

    @Column(name = "prix", nullable = false)
    private String price;


}
