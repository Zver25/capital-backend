package space.sviridovskiy.capital.income.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import space.sviridovskiy.capital.income.domain.Income;
import space.sviridovskiy.capital.income.exeption.IncomeNotFoundException;
import space.sviridovskiy.capital.income.repository.IncomeRepository;
import space.sviridovskiy.capital.income.service.IncomeService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class IncomeServiceImpl implements IncomeService {
  private final IncomeRepository expenseRepository;

  @Override
  public List<Income> findByPeriod(String username, LocalDate startDate, LocalDate endDate) {
    return expenseRepository.findByUsernameAndDateBetween(username, startDate, endDate);
  }

  @Override
  public Income findById(String username, UUID id) throws IncomeNotFoundException {
    return expenseRepository
      .findById(id)
      .filter((e -> e.getUsername().equals(username)))
      .orElseThrow(IncomeNotFoundException::new);
  }

  @Override
  public Income create(String username, Income expense) {
    expense.setId(UUID.randomUUID());
    expense.setUsername(username);

    return expenseRepository.save(expense);
  }

  @Override
  public Income update(String username, Income expense) throws IncomeNotFoundException {
    Income currentExpense = expenseRepository
      .findById(expense.getId())
      .filter(e -> e.getUsername().equals(username))
      .orElseThrow(IncomeNotFoundException::new);

    currentExpense.update(expense);

    return expenseRepository.save(currentExpense);
  }

  @Override
  public void delete(String username, UUID id) throws IncomeNotFoundException {
    Income expense = expenseRepository
      .findById(id)
      .filter(e -> e.getUsername().equals(username))
      .orElseThrow(IncomeNotFoundException::new);

    expenseRepository.delete(expense);
  }
}
