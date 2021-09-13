package com.trade.republic.quotesystem.persistence.repository;

import com.trade.republic.quotesystem.persistence.entities.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    Long deleteByIsin(String isin);
}
