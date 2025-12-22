package space.sviridovskiy.capital.report.domain.model.valueobject;

import lombok.Value;

@Value
public class CategoryId {
  String value;

  public static CategoryId of(String value) {
    if (value == null || value.trim().isEmpty()) {
      throw new IllegalArgumentException("CategoryId cannot be null or empty");
    }
    return new CategoryId(value);
  }
}
