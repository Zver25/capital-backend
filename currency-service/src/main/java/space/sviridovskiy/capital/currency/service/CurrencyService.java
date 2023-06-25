package space.sviridovskiy.capital.currency.service;

import space.sviridovskiy.capital.currency.domain.Currency;

import java.util.List;

public interface CurrencyService {
  List<Currency> getAllForUser(String username);
}
