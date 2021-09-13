package com.trade.republic.quotesystem.controllers;

import com.trade.republic.quotesystem.models.QuotePriceHistory;
import com.trade.republic.quotesystem.services.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/quotes")
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/price-history/{isin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<QuotePriceHistory> getQuotesPriceHistoryByIsin(@PathVariable String isin){
        return quoteService.getQuotesPriceHistory(isin);
    }

}
