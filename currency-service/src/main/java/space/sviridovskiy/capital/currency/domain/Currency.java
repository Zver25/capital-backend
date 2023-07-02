package space.sviridovskiy.capital.currency.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "currencies")
@Data
public class Currency {
  @Id
  @Column(name = "code")
  private String code;

  @Column(name = "name")
  private String name;
}
