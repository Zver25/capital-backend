package space.sviridovskiy.capital.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import space.sviridovskiy.capital.expense.domain.Expense;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
  List<Expense> findByUsernameAndDateBetween(String username, LocalDate startDate, LocalDate endDate);
}
