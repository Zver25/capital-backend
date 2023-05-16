package space.sviridovskiy.capital.expense.service;

import org.springframework.stereotype.Service;
import space.sviridovskiy.capital.expense.domain.Currency;

import java.util.List;

public interface CurrencyService {
  List<Currency> getAllForUser(String username);
}
