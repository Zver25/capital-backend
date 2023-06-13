package space.sviridovskiy.capital.income.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import space.sviridovskiy.capital.income.domain.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
  List<Category> findByUsername(String username);
}
