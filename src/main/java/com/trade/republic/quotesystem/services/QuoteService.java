package com.trade.republic.quotesystem.services;

import com.trade.republic.quotesystem.models.QuoteData;
import com.trade.republic.quotesystem.models.QuotePriceHistory;
import com.trade.republic.quotesystem.models.entities.Quote;
import com.trade.republic.quotesystem.repository.QuoteJdbcRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.MINUTES;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuoteService {

    private final QuoteJdbcRepository quoteJdbcRepository;

    public List<QuotePriceHistory> getQuotesPriceHistory(String isin) {
        Map<LocalDateTime, List<QuoteData>> instantListMap = groupQuotesByMinute(getQuotesPriceHistoryByIsin(isin));

        return instantListMap.entrySet().stream().map(this::createQuotePriceHistory).collect(Collectors.toList());
    }


    public Map<LocalDateTime, List<QuoteData>> groupQuotesByMinute(List<QuoteData> quoteDataList) {
        Map<LocalDateTime, List<QuoteData>> datas = new TreeMap<>();
        List<QuoteData> newQuoteDataList = new ArrayList<>();

        if (quoteDataList == null || quoteDataList.isEmpty()) return datas;

        LocalDateTime openTime = quoteDataList.get(0).getCreatedOn().truncatedTo(MINUTES);

        for (QuoteData quoteData : quoteDataList) {
            LocalDateTime createdOn = quoteData.getCreatedOn().truncatedTo(MINUTES);

            long timeDifference = MINUTES.between(openTime, createdOn);


            if (timeDifference >= 1) {
                datas.put(openTime, newQuoteDataList);
                fillOverlapedTimeInterval(timeDifference, openTime, datas);
                newQuoteDataList = new ArrayList<>();
                openTime = openTime.plusMinutes(timeDifference);
            }

            if (createdOn.equals(openTime)) {
                newQuoteDataList.add(quoteData);
            }
        }

        datas.put(openTime, newQuoteDataList);
        return datas;
    }

    public void fillOverlapedTimeInterval(long timeDifference, LocalDateTime previousTime, Map<LocalDateTime, List<QuoteData>> dateTimeListMap) {

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

    private QuotePriceHistory createQuotePriceHistory(Map.Entry<LocalDateTime, List<QuoteData>> instantListEntry) {

        QuotePriceHistory quotePriceHistory = new QuotePriceHistory();
        quotePriceHistory.setOpenTimestamp(instantListEntry.getKey());
        quotePriceHistory.setCloseTimeStamp(instantListEntry.getKey().plusMinutes(1));

        if (instantListEntry.getValue() != null && !instantListEntry.getValue().isEmpty()) {
            DoubleSummaryStatistics doubleSummaryStatistics = instantListEntry.getValue().stream().mapToDouble(QuoteData::getPrice).summaryStatistics();

            quotePriceHistory.setHighPrice(doubleSummaryStatistics.getMax());
            quotePriceHistory.setLowPrice(doubleSummaryStatistics.getMin());
            quotePriceHistory.setOpenPrice(instantListEntry.getValue().get(0).getPrice());
            quotePriceHistory.setClosePrice(instantListEntry.getValue().get(instantListEntry.getValue().size() - 1).getPrice());
        }

        return quotePriceHistory;
    }
}
