package space.sviridovskiy.capital.report.domain.model.valueobject;

import lombok.Value;
import java.math.BigDecimal;

@Value
public class MoneyAmount {
  BigDecimal value;
  CurrencyCode currencyCode;

  public static MoneyAmount of(BigDecimal value, CurrencyCode currencyCode) {
    if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Amount cannot be null or negative");
    }
    if (currencyCode == null) {
      throw new IllegalArgumentException("CurrencyCode cannot be null");
    }
    return new MoneyAmount(value, currencyCode);
  }

  public MoneyAmount add(MoneyAmount other) {
    if (!this.currencyCode.equals(other.currencyCode)) {
      throw new IllegalArgumentException("Cannot add amounts with different currencies");
    }
    return new MoneyAmount(this.value.add(other.value), this.currencyCode);
  }
}
