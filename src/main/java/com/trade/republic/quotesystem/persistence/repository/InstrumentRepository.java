package com.trade.republic.quotesystem.persistence.repository;

import com.trade.republic.quotesystem.persistence.entities.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;


public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
    @Transactional
    Long deleteByIsin(String isin);

    Optional<Instrument> findByIsin(String isin);
}
