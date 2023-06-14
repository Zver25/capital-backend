package space.sviridovskiy.capital.expense.payload;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class ExpenseResponse {
  private UUID id;

  private UUID categoryId;

  private BigDecimal amount;

  private String currencyCode;

  private LocalDate date;
}
