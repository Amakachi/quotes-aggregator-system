package com.trade.republic.quotesystem.persistence.repository;

import com.trade.republic.quotesystem.persistence.entities.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
    Long deleteByIsin(String isin);
}
