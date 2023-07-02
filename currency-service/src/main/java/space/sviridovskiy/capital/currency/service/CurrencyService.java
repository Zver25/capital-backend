package space.sviridovskiy.capital.currency.service;

import space.sviridovskiy.capital.currency.domain.Currency;
import space.sviridovskiy.capital.currency.domain.UserCurrency;

import java.util.List;

public interface CurrencyService {
  List<Currency> getAvailable();

  List<UserCurrency> getAllForUser(String username);

  List<UserCurrency> setSelectedCurrencies(String username, List<String> currencyCodes);
}
