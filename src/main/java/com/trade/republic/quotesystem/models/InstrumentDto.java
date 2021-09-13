package com.trade.republic.quotesystem.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InstrumentDto {
    private String isin;
    private String description;
    private Double latestPrice;
}
