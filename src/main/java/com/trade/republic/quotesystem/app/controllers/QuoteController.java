package com.trade.republic.quotesystem.app.controllers;

import com.trade.republic.quotesystem.domain.models.QuotePriceHistory;
import com.trade.republic.quotesystem.domain.services.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/quotes", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;

    @GetMapping(value = "/price-history/{isin}")
    public List<QuotePriceHistory> getQuotesPriceHistoryByIsin(@PathVariable String isin){
        return quoteService.getQuotesPriceHistory(isin);
    }
}
