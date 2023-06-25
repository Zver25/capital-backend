package space.sviridovskiy.capital.currency.service;

import space.sviridovskiy.capital.currency.domain.Rate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RateService {
  Optional<Rate> getRateByCurrencyPairAndDate(String sourceCode, String targetCode, LocalDate date);
  List<Rate> getRateByCurrencyPairAndPeriod(String sourceCode, String targetCode, LocalDate startDate, LocalDate endDate);
}
