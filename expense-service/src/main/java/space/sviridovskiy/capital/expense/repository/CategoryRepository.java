package space.sviridovskiy.capital.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import space.sviridovskiy.capital.expense.domain.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  List<Category> findByUsername(String username);
}
