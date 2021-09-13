package com.trade.republic.quotesystem.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuoteData {

    private Double price;
    private LocalDateTime createdOn;

    public static LocalDateTime convertTimestampToLocalDateTime(Timestamp timestamp) {
       return timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
