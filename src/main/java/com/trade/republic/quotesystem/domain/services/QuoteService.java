package com.trade.republic.quotesystem.domain.services;

import com.trade.republic.quotesystem.domain.models.QuoteData;
import com.trade.republic.quotesystem.domain.models.QuotePriceHistory;
import com.trade.republic.quotesystem.persistence.entities.Quote;
import com.trade.republic.quotesystem.persistence.repository.QuoteJdbcRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.Map.Entry;

import static java.time.temporal.ChronoUnit.MINUTES;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuoteService {

    private final QuoteJdbcRepository quoteJdbcRepository;

    public List<QuotePriceHistory> getQuotesPriceHistory(String isin) {
        Map<LocalDateTime, List<QuoteData>> instantListMap = groupQuotesByMinute(getQuotesPriceHistoryByIsin(isin));

        return instantListMap.entrySet().stream()
                .map(this::createQuotePriceHistory)
                .collect(toList());
    }

    public Map<LocalDateTime, List<QuoteData>> groupQuotesByMinute(List<QuoteData> quoteDataList) {
        Map<LocalDateTime, List<QuoteData>> data = new TreeMap<>();
        List<QuoteData> newQuoteDataList = new ArrayList<>();

        if (isNullOrEmpty(quoteDataList)) return data;

        LocalDateTime openTime = quoteDataList.get(0).getCreatedOn().truncatedTo(MINUTES);

        for (QuoteData quoteData : quoteDataList) {
            LocalDateTime createdOn = quoteData.getCreatedOn().truncatedTo(MINUTES);

            long timeDifference = MINUTES.between(openTime, createdOn);

            if (timeDifference >= 1) {
                data.put(openTime, newQuoteDataList);
                fillOverlappedTimeInterval(timeDifference, openTime, data);
                newQuoteDataList = new ArrayList<>();
                openTime = openTime.plusMinutes(timeDifference);
            }

            if (createdOn.equals(openTime)) {
                newQuoteDataList.add(quoteData);
            }
        }

        data.put(openTime, newQuoteDataList);
        return data;
    }

    public void fillOverlappedTimeInterval(long timeDifference,
                                           LocalDateTime previousTime,
                                           Map<LocalDateTime, List<QuoteData>> dateTimeListMap) {

        LocalDateTime newTime = previousTime;
        for (int i = 1; i < timeDifference; i++) {
            newTime = newTime.plusMinutes(1);
            dateTimeListMap.put(newTime, dateTimeListMap.get(previousTime));
        }
    }

    public List<QuoteData> getQuotesPriceHistoryByIsin(String isin) {
        return quoteJdbcRepository.getQuotesPriceHistory(isin);
    }

    public Long deleteQuoteByIsin(String isin) {
        return quoteJdbcRepository.deleteQuoteByIsin(isin);
    }

    public Quote saveQuote(Quote quote) {
        return quoteJdbcRepository.save(quote);
    }

    private QuotePriceHistory createQuotePriceHistory(Entry<LocalDateTime, List<QuoteData>> instantListEntry) {
        QuotePriceHistory quotePriceHistory = new QuotePriceHistory();
        quotePriceHistory.setOpenTimestamp(instantListEntry.getKey());
        quotePriceHistory.setCloseTimeStamp(instantListEntry.getKey().plusMinutes(1));

        if (!isNullOrEmpty(instantListEntry.getValue())) {
            DoubleSummaryStatistics doubleSummaryStatistics = instantListEntry.getValue().stream().mapToDouble(QuoteData::getPrice).summaryStatistics();

            quotePriceHistory.setHighPrice(doubleSummaryStatistics.getMax());
            quotePriceHistory.setLowPrice(doubleSummaryStatistics.getMin());
            quotePriceHistory.setOpenPrice(instantListEntry.getValue().get(0).getPrice());
            quotePriceHistory.setClosePrice(instantListEntry.getValue().get(instantListEntry.getValue().size() - 1).getPrice());
        }

        return quotePriceHistory;
    }

    private boolean isNullOrEmpty(Collection<?> collection) {
        return Optional.ofNullable(collection)
                .map(Collection::isEmpty)
                .orElse(true);
    }
}
