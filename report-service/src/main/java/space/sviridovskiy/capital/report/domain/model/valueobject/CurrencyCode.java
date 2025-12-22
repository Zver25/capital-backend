package space.sviridovskiy.capital.report.domain.model.valueobject;

import lombok.Value;

@Value
public class CurrencyCode {
  String value;

  public static CurrencyCode of(String value) {
    if (value == null || value.length() != 3) {
      throw new IllegalArgumentException("CurrencyCode must be 3 characters");
    }
    return new CurrencyCode(value.toUpperCase());
  }
}
