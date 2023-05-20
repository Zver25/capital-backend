package space.sviridovskiy.capital.expense.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@Data
public class Expense {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

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

  public void update(Expense expense) {
    category = expense.getCategory();
    amount = expense.getAmount();
    currencyCode = expense.getCurrencyCode();
    date = expense.getDate();
  }
}
