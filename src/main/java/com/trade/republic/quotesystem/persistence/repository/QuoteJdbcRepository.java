package com.trade.republic.quotesystem.persistence.repository;

import com.trade.republic.quotesystem.domain.models.QuoteData;
import com.trade.republic.quotesystem.persistence.entities.Quote;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.trade.republic.quotesystem.domain.utils.TimeUtil.toLocalDateTime;


@Repository
@RequiredArgsConstructor
public class QuoteJdbcRepository {
    private static final String QUOTE_HISTORY_QUERY = "select q.price, q.created_on, q.isin from quotes q where q.isin = ? " +
            "and TIMESTAMPDIFF(MINUTE,   q.created_on, current_timestamp()) <= 30 " +
            "order by q.created_on";

    private final JdbcTemplate jdbcTemplate;
    private final QuoteRepository quoteRepository;

    public List<QuoteData> getQuotesPriceHistory(String isin) {
        return jdbcTemplate.query(
                QUOTE_HISTORY_QUERY,
                preparedStatement -> preparedStatement.setString(1, isin),
                (result, rowNum) ->
                        new QuoteData(result.getDouble("price"), toLocalDateTime(result.getTimestamp("created_on")))
        );
    }

    public Long deleteQuoteByIsin(String isin) {
        return quoteRepository.deleteByIsin(isin);
    }

    public Quote save(Quote quote) {
        return quoteRepository.save(quote);
    }
}
