package com.wincore.saasgestiondestock.repositories;

import com.wincore.saasgestiondestock.entities.StockMvt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMvtRepository extends JpaRepository<StockMvt, Long> {
}
