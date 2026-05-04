package com.wincore.saasgestiondestock.dtos.stockMvt;

import com.wincore.saasgestiondestock.entities.TypeMvt;
import lombok.*;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockMvtResponse {

    private TypeMvt typeMvt;

    private Integer Quantity;

    private LocalDateTime dateMvt;

    private String comments;
}
