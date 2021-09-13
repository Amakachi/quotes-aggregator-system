package com.trade.republic.quotesystem.controllers;

import com.trade.republic.quotesystem.models.InstrumentDto;
import com.trade.republic.quotesystem.services.InstrumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/instruments")
@RequiredArgsConstructor
public class InstrumentController {

    private final InstrumentService instrumentService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InstrumentDto> getInstruments(@RequestParam(required = false, defaultValue = "10") int pageSize, @RequestParam(required = false, defaultValue = "0") int pageNumber){
        return instrumentService.getAllInstruments(pageSize, pageNumber);
    }
}
