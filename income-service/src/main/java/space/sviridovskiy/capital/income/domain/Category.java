package space.sviridovskiy.capital.income.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "categories")
@Data
public class Category {
  @Id
  @Column(name = "id")
  private UUID id;

  @Column(name = "username")
  private String username;

  @Column(name = "name")
  private String name;

  public void update(Category category) {
    name = category.getName();
  }
}
