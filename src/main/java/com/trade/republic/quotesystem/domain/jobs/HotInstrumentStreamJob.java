package com.trade.republic.quotesystem.domain.jobs;

import com.trade.republic.quotesystem.domain.services.InstrumentService;
import com.trade.republic.quotesystem.persistence.entities.Instrument;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.List;

@RequiredArgsConstructor
public class HotInstrumentStreamJob {
    private final InstrumentService instrumentService;

    @Scheduled()
    public void sendVolatileInstrumentEvent() {
        List<Instrument> instruments = instrumentService.getInstrumentsWithFirstPriceAndLastPriceForLast5Mins();

        instruments.forEach(instrument -> {
            double percentagePriceChange = calculatePriceChange(instrument.getFirstPrice(), instrument.getLastPrice());

            if (percentagePriceChange > 10 || (percentagePriceChange < 0 && percentagePriceChange < -10)) {
                //send event
                sendEventIfFlipFlop(percentagePriceChange, instrument);

            } else {
                instrumentService.updateLowPecentagePriceChange(percentagePriceChange, instrument.getIsin());

            }
        });

    }

    private void sendEventIfFlipFlop(double percentagePriceChange, Instrument instrument) {
        if (instrument.getPercentagePriceChange() != null) {
            boolean flipFlop = (percentagePriceChange - instrument.getPercentagePriceChange()) < 20;

            if (flipFlop) {
                //send event
            }

            instrument.setPercentagePriceChange(null);
            instrumentService.saveInstrument(instrument);
        }
    }

    private double calculatePriceChange(Double firstPrice, Double lastPrice) {
        return (firstPrice != null && lastPrice != null) ? ((lastPrice - firstPrice) / firstPrice) * 100 : 0;
    }

}
