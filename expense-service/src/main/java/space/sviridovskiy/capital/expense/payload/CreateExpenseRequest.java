package space.sviridovskiy.capital.expense.payload;

import lombok.Data;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class CreateExpenseRequest {
  @Positive(message = "CategoryId is required")
  private UUID categoryId;

  @Positive(message = "Amount cannot be negative")
  private BigDecimal amount;

  @Positive(message = "CurrencyCode is required")
  private String currencyCode;

  @PastOrPresent(message = "Expense cannot be in future")
  private LocalDate date;
}
