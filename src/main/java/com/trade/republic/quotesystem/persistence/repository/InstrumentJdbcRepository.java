package com.trade.republic.quotesystem.persistence.repository;

import com.trade.republic.quotesystem.domain.models.InstrumentDto;
import com.trade.republic.quotesystem.domain.models.VolatileInstrumentDto;
import com.trade.republic.quotesystem.persistence.entities.Instrument;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InstrumentJdbcRepository {

    private static final String ALL_INSTRUMENTS_QUERY =  "select i.isin, i.description, (select q.price from quotes q where q.isin = i.isin order by q.created_on desc limit 1) as latest_price  from instruments i limit ? offset ?";
    private static final String ALL_INSTRUMENTS_AND_PRICE_LAST_5_MINS_QUERY = "select i.isin, i.description, (select q.price from quotes q where q.isin = i.isin and TIMESTAMPDIFF(MINUTE,   q.created_on, current_timestamp()) <= 5  order by q.created_on desc limit 1) as last_price, " +
            "(select q.price from quotes q where q.isin = i.isin and TIMESTAMPDIFF(MINUTE,   q.created_on, current_timestamp()) <= 5 order by q.created_on limit 1) as first_price" +
            " from instruments i";
    private static final String UPDATE_INSTRUMENT_BY_ISIN = "update instruments set percentage_price_change = ? where isin = ?";

    private final JdbcTemplate jdbcTemplate;
    private final InstrumentRepository instrumentRepository;

    public List<InstrumentDto> getAllInstruments(int pageSize, int pageNumber){
        return jdbcTemplate.query(ALL_INSTRUMENTS_QUERY,
                preparedStatement -> {
                    preparedStatement.setInt(1, pageSize);
                    preparedStatement.setInt(2, pageNumber);
                },
                (result, rowNum) -> new InstrumentDto(result.getString("isin"), result.getString("description"), result.getDouble("latest_price")));
    }

    public List<Instrument> getInstrumentsWithFirstPriceAndLastPriceForLast5Mins(){
        return jdbcTemplate.query(ALL_INSTRUMENTS_AND_PRICE_LAST_5_MINS_QUERY,
                (result, rowNum) -> new Instrument(result.getString("isin"), result.getString("description"), result.getDouble("first_price"), result.getDouble("last_price")));
    }

    public int updateInstrumentByIsin(Double priceChange, String isin){
        return jdbcTemplate.update(UPDATE_INSTRUMENT_BY_ISIN,
                preparedStatement -> {
                    preparedStatement.setDouble(1, priceChange);
                    preparedStatement.setString(2, isin);
                });
    }

    public Long deleteInstrumentByIsin(String isin) {
        return instrumentRepository.deleteByIsin(isin);
    }

    public Instrument save(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }

    public Instrument findByIsin(String isin){
        return instrumentRepository.findByIsin(isin).orElse(null);
    }

}
