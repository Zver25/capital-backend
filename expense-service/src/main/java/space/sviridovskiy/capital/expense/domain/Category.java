package space.sviridovskiy.capital.expense.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Data
public class Category {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "username")
  private String username;

  @Column(name = "name")
  private String name;

  public void update(Category category) {
    name = category.getName();
  }
}
