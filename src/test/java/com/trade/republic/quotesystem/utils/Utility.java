package com.trade.republic.quotesystem.utils;

import com.trade.republic.quotesystem.models.InstrumentDto;
import com.trade.republic.quotesystem.models.entities.Instrument;
import com.trade.republic.quotesystem.models.entities.Quote;

import java.util.List;

public class Utility {

    public static Instrument createInstrument(){
        Instrument instrument = new Instrument();
        instrument.setDescription("test");
        instrument.setIsin("UH3165583681");
        return instrument;
    }

    public static Quote createQuote(){
        Quote quote = new Quote();
        quote.setPrice(696.372);
        quote.setIsin("UH3165583681");
        return quote;
    }

    public static int getSizeOfInstrumentsList(List<InstrumentDto> instrumentDtoList){
        return instrumentDtoList.size();
    }
}
