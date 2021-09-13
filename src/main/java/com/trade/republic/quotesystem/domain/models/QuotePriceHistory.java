package com.trade.republic.quotesystem.domain.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuotePriceHistory {
    private Double openPrice;
    private Double highPrice;
    private Double lowPrice;
    private Double closePrice;
    private LocalDateTime openTimestamp;
    private LocalDateTime closeTimeStamp;
}
