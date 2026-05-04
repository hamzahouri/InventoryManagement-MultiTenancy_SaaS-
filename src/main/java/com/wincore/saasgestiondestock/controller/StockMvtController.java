package com.wincore.saasgestiondestock.controller;

import com.wincore.saasgestiondestock.dtos.product.ProductRequest;
import com.wincore.saasgestiondestock.dtos.product.ProductResponse;
import com.wincore.saasgestiondestock.dtos.stockMvt.StockMvtRequest;
import com.wincore.saasgestiondestock.dtos.stockMvt.StockMvtResponse;
import com.wincore.saasgestiondestock.services.StoctMvtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1/stockMvts")
@RequiredArgsConstructor
public class StockMvtController {

    private final StoctMvtService service;


    @PostMapping
    public ResponseEntity<Void> createStockMvt(
            @Valid
            @RequestBody StockMvtRequest request
    ) {
        service.create(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/id")
    public ResponseEntity<Void> updateStockMvt(
            @Valid
            @RequestBody StockMvtRequest request,
            @PathVariable("id") Long id

    ) {
        service.update(id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/id")
    public ResponseEntity<StockMvtResponse> getStockMvt(

            @PathVariable("id") Long id

    ) {
        service.findById(id);
        return ResponseEntity.ok(this.service.findById(id));
    }

    @GetMapping()
    public ResponseEntity<List<StockMvtResponse>> getStockMvt(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "0") int size
    ) {

        return ResponseEntity.ok(this.service.findAll(page,size));
    }


    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteStockMvt(
            @PathVariable("id") Long id
    ) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
