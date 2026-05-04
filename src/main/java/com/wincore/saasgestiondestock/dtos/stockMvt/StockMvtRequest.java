package com.wincore.saasgestiondestock.dtos.stockMvt;

import com.wincore.saasgestiondestock.entities.TypeMvt;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockMvtRequest {

    private TypeMvt typeMvt;

    private Integer Quantity;

    private LocalDateTime dateMvt;

    private String comments;

    private Long productId;
}
