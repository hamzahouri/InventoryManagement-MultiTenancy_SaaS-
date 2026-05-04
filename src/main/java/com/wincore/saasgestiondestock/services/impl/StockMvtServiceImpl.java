package com.wincore.saasgestiondestock.services.impl;

import com.wincore.saasgestiondestock.dtos.stockMvt.StockMvtRequest;
import com.wincore.saasgestiondestock.dtos.stockMvt.StockMvtResponse;
import com.wincore.saasgestiondestock.entities.Product;
import com.wincore.saasgestiondestock.entities.StockMvt;
import com.wincore.saasgestiondestock.mappers.StockMvtMapper;
import com.wincore.saasgestiondestock.repositories.ProductRepository;
import com.wincore.saasgestiondestock.repositories.StockMvtRepository;
import com.wincore.saasgestiondestock.services.StoctMvtService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockMvtServiceImpl implements StoctMvtService {

    private final StockMvtRepository stockMvtRepository;
    private final StockMvtMapper stockMvtMapper;
    private final ProductRepository productRepository;

    @Override
    public void create(StockMvtRequest request) {
        chekIfProductExist(request.getProductId());

        final StockMvt stockMvt = stockMvtMapper.toEntity(request);
        stockMvtRepository.save(stockMvt);
    }

    @Override
    public void update(Long id, StockMvtRequest request) {
        final Optional<StockMvt> stockMvt = stockMvtRepository.findById(id);
        if(stockMvt.isEmpty()) {
            throw new EntityNotFoundException("stockMVT not exist");
        }
        chekIfProductExist(request.getProductId());

        final StockMvt updated = stockMvtMapper.toEntity(request);
        updated.setId(id);
        stockMvtRepository.save(updated);


    }

    @Override
    public List<StockMvtResponse> findAll(int page, int size) {
        List<StockMvt> stockMvts = stockMvtRepository.findAll();


        return stockMvts.stream().map(stockMvtMapper::toResponse).toList();
    }

    @Override
    public StockMvtResponse findById(Long id) {
        return this.stockMvtRepository.findById(id).map(stockMvtMapper::toResponse).orElseThrow(
                () -> new EntityNotFoundException(" Not found")
        );
    }

    @Override
    public void delete(Long id) {
        StockMvt stockMvt = this.stockMvtRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException(" not found")
        );
            this.stockMvtRepository.delete(stockMvt);
    }

    private void chekIfProductExist (final Long productId) {
        final Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty()) {

            throw new EntityNotFoundException("Product not exist");
        }

    }
}
