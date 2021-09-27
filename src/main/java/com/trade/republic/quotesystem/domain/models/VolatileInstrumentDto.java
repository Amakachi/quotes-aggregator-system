package com.trade.republic.quotesystem.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VolatileInstrumentDto {
    private String isin;
    private String description;
    private Double firstPrice;
    private Double lastPrice;
}
