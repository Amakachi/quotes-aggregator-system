package com.trade.republic.quotesystem.repository;

import com.trade.republic.quotesystem.models.entities.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
    Long deleteByIsin(String isin);
}
