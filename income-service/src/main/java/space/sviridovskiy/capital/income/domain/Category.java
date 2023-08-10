package space.sviridovskiy.capital.income.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
public class Category {
  @Id
  @Column(name = "id")
  private UUID id;

  @Column(name = "username")
  private String username;

  @Column(name = "name")
  private String name;

  @Column(name = "is_disabled")
  private boolean isDisabled;

  public Category(UUID id) {
    this.id = id;
  }
}
