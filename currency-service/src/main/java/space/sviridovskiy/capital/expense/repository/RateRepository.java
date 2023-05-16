package space.sviridovskiy.capital.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import space.sviridovskiy.capital.expense.domain.Rate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RateRepository extends JpaRepository<Rate, Long> {
  Optional<Rate> findBySourceCodeAndTargetCodeAndDate(String sourceCode, String targetCode, LocalDate date);
  List<Rate> findAllBySourceCodeAndTargetCodeAndDateBetween(String sourceCode, String targetCode, LocalDate startDate, LocalDate endDate);
}
