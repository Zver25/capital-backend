package space.sviridovskiy.capital.income.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import space.sviridovskiy.capital.income.domain.Category;
import space.sviridovskiy.capital.income.domain.Income;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IncomeRepository extends JpaRepository<Income, UUID> {
  List<Income> findByUsernameAndDateBetween(String username, LocalDate startDate, LocalDate endDate);
  List<Income> findByUsernameAndCategoryAndDateBetween(String username, Category category, LocalDate startDate, LocalDate endDate);
}
