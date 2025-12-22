package space.sviridovskiy.capital.report.domain.model.valueobject;

import lombok.Value;

@Value
public class Month {
  int value;

  public static Month of(int value) {
    if (value < 1 || value > 12) {
      throw new IllegalArgumentException("Month must be between 1 and 12");
    }
    return new Month(value);
  }
}
