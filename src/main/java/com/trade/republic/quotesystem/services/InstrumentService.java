package com.trade.republic.quotesystem.services;

import com.trade.republic.quotesystem.models.InstrumentDto;
import com.trade.republic.quotesystem.models.entities.Instrument;
import com.trade.republic.quotesystem.repository.InstrumentJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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


}
