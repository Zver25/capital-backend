package space.sviridovskiy.capital.currency.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import space.sviridovskiy.capital.currency.domain.Currency;
import space.sviridovskiy.capital.currency.repository.CurrencyRepository;
import space.sviridovskiy.capital.currency.service.CurrencyService;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {
  private final CurrencyRepository currencyRepository;

  @Override
  public List<Currency> getAllForUser(String username) {
    return currencyRepository.findByUsername(username);
  }
}
