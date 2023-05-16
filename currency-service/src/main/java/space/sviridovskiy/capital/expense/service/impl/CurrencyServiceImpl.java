package space.sviridovskiy.capital.expense.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import space.sviridovskiy.capital.expense.domain.Currency;
import space.sviridovskiy.capital.expense.repository.CurrencyRepository;
import space.sviridovskiy.capital.expense.service.CurrencyService;

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
