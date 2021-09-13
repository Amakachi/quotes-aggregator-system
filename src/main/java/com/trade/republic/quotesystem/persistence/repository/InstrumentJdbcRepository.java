package com.trade.republic.quotesystem.persistence.repository;

import com.trade.republic.quotesystem.domain.models.InstrumentDto;
import com.trade.republic.quotesystem.persistence.entities.Instrument;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InstrumentJdbcRepository {
    private final JdbcTemplate jdbcTemplate;
    private final InstrumentRepository instrumentRepository;

    public List<InstrumentDto> getAllInstruments(int pageSize, int pageNumber){
        return jdbcTemplate.query("select i.isin, i.description, (select q.price from quotes q where q.isin = i.isin order by q.created_on desc limit 1) as latest_price  from instruments i limit "+ pageSize+ "offset "+ pageNumber,
                (result, rowNum) -> new InstrumentDto(result.getString("isin"), result.getString("description"), result.getDouble("latest_price")));
    }

    public Long deleteInstrumentByIsin(String isin) {
        return instrumentRepository.deleteByIsin(isin);
    }

    public Instrument save(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }



}
