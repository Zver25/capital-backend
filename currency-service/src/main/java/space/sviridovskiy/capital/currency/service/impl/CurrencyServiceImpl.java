package space.sviridovskiy.capital.currency.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import space.sviridovskiy.capital.currency.domain.Currency;
import space.sviridovskiy.capital.currency.domain.UserCurrency;
import space.sviridovskiy.capital.currency.repository.CurrencyRepository;
import space.sviridovskiy.capital.currency.repository.UserCurrencyRepository;
import space.sviridovskiy.capital.currency.service.CurrencyService;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {
  private final UserCurrencyRepository userCurrencyRepository;
  private final CurrencyRepository currencyRepository;

  @Override
  public List<UserCurrency> getAllForUser(String username) {
    return userCurrencyRepository.findByUsername(username);
  }

  @Override
  public List<UserCurrency> setSelectedCurrencies(String username, List<String> currencyCodes) {
    final List<UserCurrency> userCurrencies = getAllForUser(username);

    userCurrencies.stream()
      .filter(userCurrency -> !currencyCodes.contains(userCurrency.getCode()))
      .forEach(userCurrencyRepository::delete);

    currencyCodes.stream()
      .filter(code -> userCurrencies.stream()
        .noneMatch(userCurrency -> userCurrency.getCode().equals(code))
      )
      .forEach(code -> {
        final UserCurrency userCurrency = new UserCurrency();

        userCurrency.setUsername(username);
        userCurrency.setCode(code);

        userCurrencyRepository.save(userCurrency);
      });

    return getAllForUser(username);
  }

  @Override
  public List<Currency> getAvailable() {
    return currencyRepository.findAll();
  }
}
