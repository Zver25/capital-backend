package space.sviridovskiy.capital.currency.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_currencies")
@Data
public class UserCurrency {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "code")
  private String code;

  @Column(name = "username")
  private String username;
}
