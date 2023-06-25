package space.sviridovskiy.capital.currency.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "currencies")
@Data
public class Currency {
  @Id
  @Column(name = "id")
  private long id;

  @Column(name = "code")
  private String code;

  @Column(name = "username")
  private String username;
}
