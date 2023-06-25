package space.sviridovskiy.capital.currency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import space.sviridovskiy.capital.currency.domain.Currency;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
  List<Currency> findByUsername(String username);
}
