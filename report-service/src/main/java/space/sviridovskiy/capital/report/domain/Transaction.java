package space.sviridovskiy.capital.report.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class Transaction {
  private UUID id;

  private String username;

  private String categoryId;

  private BigDecimal amount;

  private String currencyCode;

  private LocalDate date;

  public int getMonth() {
    return date.getMonthValue();
  }
}
