package com.trade.republic.quotesystem.websockets;

import com.google.gson.Gson;
import com.trade.republic.quotesystem.enums.InstrumentType;
import com.trade.republic.quotesystem.enums.QuoteType;
import com.trade.republic.quotesystem.models.Response;
import com.trade.republic.quotesystem.models.entities.Instrument;
import com.trade.republic.quotesystem.models.entities.Quote;
import com.trade.republic.quotesystem.services.InstrumentService;
import com.trade.republic.quotesystem.services.QuoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebSocketService {

    private final Gson gson;

    private final QuoteService quoteService;

    private final InstrumentService instrumentService;

    @Transactional
    public void saveSocketStream(String responseJson) {
        Response response = convertJsonToObject(responseJson, Response.class);

        if (QuoteType.QUOTE.name().equalsIgnoreCase(response.getType())) {
            Quote quote = convertJsonToObject(response.getData().toString(), Quote.class);
            quoteService.saveQuote(quote);
            log.info("logging quotes saved successfully {}", quote);
        } else {
            Instrument instrument = convertJsonToObject(response.getData().toString(), Instrument.class);
            if (instrument != null) {
                compareInstrumentTypeToSaveOrDelete(response.getType(), instrument);
            }
            log.info("logging instruments saved successfully {}", instrument);
        }
    }

    public void compareInstrumentTypeToSaveOrDelete(String type, Instrument instrument) {

        switch (InstrumentType.valueOf(type.toUpperCase())) {

            case ADD:
                instrumentService.saveInstrument(instrument);
                break;
            case DELETE:
                Long numberOfDeletedQuotes  = quoteService.deleteQuoteByIsin(instrument.getIsin());
                instrumentService.deleteInstrumentByIsin(instrument.getIsin());

                log.info("deleted {} quotes with isin {} successfully",numberOfDeletedQuotes, instrument.getIsin());
                break;
            default:
        }
    }

    private <T> T convertJsonToObject(String json, Class<T> tClass) {
        return gson.fromJson(json, tClass);
    }

}
