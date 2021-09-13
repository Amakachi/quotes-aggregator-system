package com.trade.republic.quotesystem.repository;

import com.trade.republic.quotesystem.models.InstrumentDto;
import com.trade.republic.quotesystem.models.QuoteData;
import com.trade.republic.quotesystem.models.entities.Quote;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class QuoteJdbcRepository {

    private final JdbcTemplate jdbcTemplate;
    private final QuoteRepository quoteRepository;

    public List<QuoteData> getQuotesPriceHistory(String isin){

        return jdbcTemplate.query("select q.price, q.created_on, q.isin from quotes q where q.isin =  '"+ isin +"' and TIMESTAMPDIFF(MINUTE,   q.created_on, current_timestamp()) <= 30 order by q.created_on",
                (result, rowNum) -> new QuoteData( result.getDouble("price"), QuoteData.convertTimestampToLocalDateTime(result.getTimestamp("created_on"))));
    }


    public Long deleteQuoteByIsin(String isin) {
       return quoteRepository.deleteByIsin(isin);
    }

    public Quote save(Quote quote) {
        return quoteRepository.save(quote);
    }

}
