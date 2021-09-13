package com.trade.republic.quotesystem.app.controllers;

import com.trade.republic.quotesystem.domain.models.InstrumentDto;
import com.trade.republic.quotesystem.domain.services.InstrumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/instruments", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class InstrumentController {

    private final InstrumentService instrumentService;

    @GetMapping
    public List<InstrumentDto> getInstruments(@RequestParam(required = false, defaultValue = "10") int pageSize,
                                              @RequestParam(required = false, defaultValue = "0") int pageNumber) {
        return instrumentService.getAllInstruments(pageSize, pageNumber);
    }
}
