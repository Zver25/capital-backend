package space.sviridovskiy.capital.currency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import space.sviridovskiy.capital.currency.domain.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
