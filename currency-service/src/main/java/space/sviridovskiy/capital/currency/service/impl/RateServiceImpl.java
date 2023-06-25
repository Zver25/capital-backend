package space.sviridovskiy.capital.currency.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import space.sviridovskiy.capital.currency.domain.Rate;
import space.sviridovskiy.capital.currency.repository.RateRepository;
import space.sviridovskiy.capital.currency.service.RateService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class RateServiceImpl implements RateService {
  private final RateRepository rateRepository;

  @Override
  public Optional<Rate> getRateByCurrencyPairAndDate(String sourceCode, String targetCode, LocalDate date) {
    return rateRepository.findBySourceCodeAndTargetCodeAndDate(sourceCode, targetCode, date);
  }

  @Override
  public List<Rate> getRateByCurrencyPairAndPeriod(String sourceCode, String targetCode, LocalDate startDate, LocalDate endDate) {
    return rateRepository.findAllBySourceCodeAndTargetCodeAndDateBetween(sourceCode, targetCode, startDate, endDate);
  }
}
