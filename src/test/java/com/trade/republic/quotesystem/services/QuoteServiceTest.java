package com.trade.republic.quotesystem.services;


import com.trade.republic.quotesystem.domain.models.QuoteData;
import com.trade.republic.quotesystem.domain.services.QuoteService;
import com.trade.republic.quotesystem.persistence.entities.Quote;
import com.trade.republic.quotesystem.persistence.repository.QuoteJdbcRepository;
import com.trade.republic.quotesystem.utils.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuoteServiceTest {

    @Mock
    private QuoteJdbcRepository quoteJdbcRepository;

    private QuoteService quoteService;


    @BeforeEach
    public void contextLoads(){
        quoteService = new QuoteService(quoteJdbcRepository);
    }

    @Test
    public void delete_quote_isin() {

        when(quoteJdbcRepository.deleteQuoteByIsin(Mockito.anyString())).thenReturn(1L);
        long numberOfDeletedQuote = quoteService.deleteQuoteByIsin(Utility.createQuote().getIsin());
        assertEquals(numberOfDeletedQuote, 1);
    }

    @Test
    public void save_quote_then_return() {
        when(quoteJdbcRepository.save(any(Quote.class))).thenReturn(Utility.createQuote());
        Quote quote = quoteService.saveQuote(Utility.createQuote());
        assertNotNull(quote);
    }

    @Test
    public void get_quotes_price_history_by_isin_then_return() {
        List<QuoteData> quoteDataList = new ArrayList<>();
        QuoteData quoteData = new QuoteData();
        quoteData.setPrice(1234.56);
        quoteDataList.add(quoteData);

        when(quoteJdbcRepository.getQuotesPriceHistory(anyString())).thenReturn(quoteDataList);
        List<QuoteData> result = quoteService.getQuotesPriceHistoryByIsin("1234567827");
        assertNotNull(result);
    }

}