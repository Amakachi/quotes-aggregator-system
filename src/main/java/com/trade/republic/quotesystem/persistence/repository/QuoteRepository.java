package com.trade.republic.quotesystem.persistence.repository;

import com.trade.republic.quotesystem.persistence.entities.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    @Transactional
    Long deleteByIsin(String isin);
}
