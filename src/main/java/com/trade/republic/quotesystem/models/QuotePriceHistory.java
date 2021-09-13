package com.trade.republic.quotesystem.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuotePriceHistory {
    private LocalDateTime openTimestamp;
    private Double openPrice;
    private Double highPrice;
    private Double lowPrice;
    private Double closePrice;
    private LocalDateTime closeTimeStamp;
}
