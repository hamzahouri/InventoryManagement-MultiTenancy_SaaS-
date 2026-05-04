package com.wincore.saasgestiondestock.dtos.product;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private String name;

    private String reference;

    private String description;

    private String alertThreshold;

    private String price;

    private String categoryName;

    private int availableQuantity;
}
