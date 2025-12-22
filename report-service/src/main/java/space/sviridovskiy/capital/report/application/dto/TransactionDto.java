package space.sviridovskiy.capital.report.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
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
