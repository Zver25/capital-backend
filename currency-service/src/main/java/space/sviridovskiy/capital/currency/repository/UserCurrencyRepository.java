package space.sviridovskiy.capital.currency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import space.sviridovskiy.capital.currency.domain.UserCurrency;

import java.util.List;

public interface UserCurrencyRepository extends JpaRepository<UserCurrency, Long> {
  List<UserCurrency> findByUsername(String username);
}
