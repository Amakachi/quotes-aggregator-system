package com.trade.republic.quotesystem.services;

import com.trade.republic.quotesystem.models.InstrumentDto;
import com.trade.republic.quotesystem.models.entities.Instrument;
import com.trade.republic.quotesystem.repository.InstrumentJdbcRepository;
import com.trade.republic.quotesystem.utils.Utility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyInt;


@ExtendWith(MockitoExtension.class)
public class InstrumentServiceTest {

    @Mock
    private InstrumentJdbcRepository instrumentJdbcRepository;

    private InstrumentService instrumentService;

    @BeforeEach
    public void contextLoads(){
        instrumentService = new InstrumentService(instrumentJdbcRepository);
    }


    @Test
    public void save_instrument_then_return_instrument() {
        when(instrumentJdbcRepository.save(any(Instrument.class))).thenReturn(Utility.createInstrument());
        Instrument instrument = instrumentService.saveInstrument(Utility.createInstrument());

        assertNotNull(instrument);
    }

    @Test
    public void given_isin_return_number_of_deleted_instrument() {
        when(instrumentJdbcRepository.deleteInstrumentByIsin(Mockito.anyString())).thenReturn(1L);
        long numberOfDeletedInstruments= instrumentService.deleteInstrumentByIsin(Utility.createInstrument().getIsin());

        assertEquals(numberOfDeletedInstruments, 1);
    }

    @Test
    public void given_isin_return_number_of_deleted_instrument_as_zero() {
        when(instrumentJdbcRepository.deleteInstrumentByIsin(Mockito.anyString())).thenReturn(0L);
        long numberOfDeletedInstruments= instrumentService.deleteInstrumentByIsin(Utility.createInstrument().getIsin());

        assertEquals(numberOfDeletedInstruments, 0);
    }

    @Test
    public void instruments_exists_in_database() {
        List<InstrumentDto> instrumentDtoList = new ArrayList<>();
        InstrumentDto instrumentDto = new InstrumentDto();
        instrumentDto.setIsin("122344566");
        instrumentDto.setLatestPrice(1256.78);
        instrumentDtoList.add(instrumentDto);

        when(instrumentJdbcRepository.getAllInstruments(anyInt(), anyInt())).thenReturn(instrumentDtoList);
        List<InstrumentDto> result = instrumentService.getAllInstruments(10, 0);

        assertNotNull(result);
    }
}