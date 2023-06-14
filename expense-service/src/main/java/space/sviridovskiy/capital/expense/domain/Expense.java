package space.sviridovskiy.capital.expense.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "expenses")
@Data
public class Expense {
  @Id
  @Column(name = "id")
  private UUID id;

  @Column(name = "username")
  private String username;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @Column(name = "amount")
  private BigDecimal amount;

  @Column(name = "currency_code")
  private String currencyCode;

  @Column(name = "date")
  private LocalDate date;
}
