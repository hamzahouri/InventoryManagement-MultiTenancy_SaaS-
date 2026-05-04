package com.wincore.saasgestiondestock.mappers;

import com.wincore.saasgestiondestock.dtos.stockMvt.StockMvtRequest;
import com.wincore.saasgestiondestock.dtos.stockMvt.StockMvtResponse;
import com.wincore.saasgestiondestock.entities.Product;
import com.wincore.saasgestiondestock.entities.StockMvt;
import org.springframework.stereotype.Component;

@Component
public class StockMvtMapper {


    public StockMvt toEntity (final StockMvtRequest stockMvtRequest) {

        return StockMvt.builder()
                .dateMvt(stockMvtRequest.getDateMvt())
                .product(Product.builder().id(stockMvtRequest.getProductId()).build())
                .Quantity(stockMvtRequest.getQuantity())
                .typeMvt(stockMvtRequest.getTypeMvt())
                .comments(stockMvtRequest.getComments())
                .build();
    }

    public StockMvtResponse toResponse (final StockMvt stockMvt) {

        return StockMvtResponse.builder()
                .dateMvt(stockMvt.getDateMvt())
                .Quantity(stockMvt.getQuantity())
                .typeMvt(stockMvt.getTypeMvt())
                .comments(stockMvt.getComments())
                .build();
    }
}
