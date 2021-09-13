package com.trade.republic.quotesystem.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuoteData {
    private Double price;
    private LocalDateTime createdOn;
}
