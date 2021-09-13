package com.trade.republic.quotesystem.domain.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimeUtil {
    private TimeUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
