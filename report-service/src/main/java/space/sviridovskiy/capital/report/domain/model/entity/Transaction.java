package space.sviridovskiy.capital.report.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import space.sviridovskiy.capital.report.domain.model.valueobject.CategoryId;
import space.sviridovskiy.capital.report.domain.model.valueobject.MoneyAmount;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class Transaction {
  private final UUID id;
  private final String username;
  private final CategoryId categoryId;
  private final MoneyAmount amount;
  private final LocalDate date;

  public int getMonth() {
    return date.getMonthValue();
  }
}
