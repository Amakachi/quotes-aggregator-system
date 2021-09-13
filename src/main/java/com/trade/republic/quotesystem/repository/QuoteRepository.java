package com.trade.republic.quotesystem.repository;

import com.trade.republic.quotesystem.models.entities.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    Long deleteByIsin(String isin);
}
