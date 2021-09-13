package com.trade.republic.quotesystem.utils;

import com.trade.republic.quotesystem.persistence.entities.Instrument;
import com.trade.republic.quotesystem.persistence.entities.Quote;

public class TestDataFixture {

    public static Instrument createInstrument() {
        Instrument instrument = new Instrument();
        instrument.setDescription("test");
        instrument.setIsin("UH3165583681");
        return instrument;
    }

    public static Quote createQuote() {
        Quote quote = new Quote();
        quote.setPrice(696.372);
        quote.setIsin("UH3165583681");
        return quote;
    }
}
