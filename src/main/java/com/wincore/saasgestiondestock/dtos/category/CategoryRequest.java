package com.wincore.saasgestiondestock.dtos.category;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest {

    private String name;

    private String description;

}
