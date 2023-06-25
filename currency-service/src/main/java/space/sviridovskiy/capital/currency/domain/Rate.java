package space.sviridovskiy.capital.currency.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "rates")
@Data
public class Rate {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "source_code")
  private String sourceCode;

  @Column(name = "target_code")
  private String targetCode;

  @Column(name = "date")
  private LocalDate date;

  @Column(name = "rate")
  private BigDecimal rate;
}
