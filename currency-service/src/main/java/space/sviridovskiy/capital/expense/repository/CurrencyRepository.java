package space.sviridovskiy.capital.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import space.sviridovskiy.capital.expense.domain.Currency;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
  List<Currency> findByUsername(String username);
}
