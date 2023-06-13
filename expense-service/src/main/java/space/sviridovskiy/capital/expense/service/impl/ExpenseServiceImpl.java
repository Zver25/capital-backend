package space.sviridovskiy.capital.expense.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import space.sviridovskiy.capital.expense.domain.Expense;
import space.sviridovskiy.capital.expense.exeption.ExpenseNotFoundException;
import space.sviridovskiy.capital.expense.repository.ExpenseRepository;
import space.sviridovskiy.capital.expense.service.ExpenseService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {
  private final ExpenseRepository expenseRepository;

  @Override
  public List<Expense> findByPeriod(String username, LocalDate startDate, LocalDate endDate) {
    return expenseRepository.findByUsernameAndDateBetween(username, startDate, endDate);
  }

  @Override
  public Expense findById(String username, UUID id) throws ExpenseNotFoundException {
    return expenseRepository
      .findById(id)
      .filter((e -> e.getUsername().equals(username)))
      .orElseThrow(ExpenseNotFoundException::new);
  }

  @Override
  public Expense create(String username, Expense expense) {
    expense.setId(UUID.randomUUID());
    expense.setUsername(username);

    return expenseRepository.save(expense);
  }

  @Override
  public Expense update(String username, Expense expense) throws ExpenseNotFoundException {
    Expense currentExpense = expenseRepository
      .findById(expense.getId())
      .filter(e -> e.getUsername().equals(username))
      .orElseThrow(ExpenseNotFoundException::new);

    currentExpense.update(expense);

    return expenseRepository.save(currentExpense);
  }

  @Override
  public void delete(String username, UUID id) throws ExpenseNotFoundException {
    Expense expense = expenseRepository
      .findById(id)
      .filter(e -> e.getUsername().equals(username))
      .orElseThrow(ExpenseNotFoundException::new);

    expenseRepository.delete(expense);
  }
}
