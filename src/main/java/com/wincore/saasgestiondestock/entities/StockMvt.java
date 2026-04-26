package com.wincore.saasgestiondestock.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "StockMvts")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class StockMvt extends AbstractEntity{

    @Column(name = "type_Mouvement", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeMvt typeMvt;

    @Column(name = "Quantity", nullable = false)
    private Integer Quantity;

    @Column(name = "date_mouvement", nullable = false)
    private LocalDateTime dateMvt;

    @Column(name = "comment")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
