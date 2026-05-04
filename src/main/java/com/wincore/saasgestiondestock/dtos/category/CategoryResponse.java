package com.wincore.saasgestiondestock.dtos.category;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {

    private String name;

    private String description;

    private int nbProducts;
}
