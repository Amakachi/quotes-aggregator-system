package com.trade.republic.quotesystem.domain.services;

import com.trade.republic.quotesystem.domain.models.InstrumentDto;
import com.trade.republic.quotesystem.persistence.entities.Instrument;
import com.trade.republic.quotesystem.persistence.repository.InstrumentJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstrumentService {

    private final InstrumentJdbcRepository instrumentJdbcRepository;

    public Long deleteInstrumentByIsin(String isin) {
        return instrumentJdbcRepository.deleteInstrumentByIsin(isin);
    }

    public Instrument saveInstrument(Instrument instrument) {
        return instrumentJdbcRepository.save(instrument);
    }

    public List<InstrumentDto> getAllInstruments(int pageSize, int pageNumber) {
        return instrumentJdbcRepository.getAllInstruments(pageSize, pageNumber);
    }

    public List<Instrument> getInstrumentsWithFirstPriceAndLastPriceForLast5Mins(){
       return instrumentJdbcRepository.getInstrumentsWithFirstPriceAndLastPriceForLast5Mins();
    }

    public Instrument findByIsin(String isin){
        return instrumentJdbcRepository.findByIsin(isin);
    }

    public int updateLowPecentagePriceChange(Double priceChange, String isin){
        return instrumentJdbcRepository.updateInstrumentByIsin(priceChange, isin);
    }


}
