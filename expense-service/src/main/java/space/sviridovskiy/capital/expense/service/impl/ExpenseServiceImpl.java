package space.sviridovskiy.capital.expense.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import space.sviridovskiy.capital.expense.payload.CategoryResponse;
import space.sviridovskiy.capital.expense.payload.CreateExpenseRequest;
import space.sviridovskiy.capital.expense.payload.UpdateExpenseRequest;
import space.sviridovskiy.capital.expense.domain.Category;
import space.sviridovskiy.capital.expense.domain.Expense;
import space.sviridovskiy.capital.expense.exeption.CategoryNotFoundException;
import space.sviridovskiy.capital.expense.exeption.ExpenseNotFoundException;
import space.sviridovskiy.capital.expense.repository.ExpenseRepository;
import space.sviridovskiy.capital.expense.service.CategoryService;
import space.sviridovskiy.capital.expense.service.ExpenseService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {
  private final ExpenseRepository expenseRepository;
  private final CategoryService categoryService;

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
  public Expense create(String username, CreateExpenseRequest request) throws CategoryNotFoundException {
    final CategoryResponse categoryResponse = categoryService.findById(username, request.getCategoryId());
    final Category category = new Category(categoryResponse.getId());

    final Expense expense = new Expense();
    expense.setId(UUID.randomUUID());
    expense.setUsername(username);
    expense.setCategory(category);
    expense.setAmount(request.getAmount());
    expense.setCurrencyCode(request.getCurrencyCode());
    expense.setDate(request.getDate());

    return expenseRepository.save(expense);
  }

  @Override
  public Expense update(String username, UpdateExpenseRequest request) throws ExpenseNotFoundException, CategoryNotFoundException {
    final CategoryResponse categoryResponse = categoryService.findById(username, request.getCategoryId());
    final Category category = new Category(categoryResponse.getId());

    final Expense currentExpense = expenseRepository
      .findById(request.getId())
      .filter(e -> e.getUsername().equals(username))
      .orElseThrow(ExpenseNotFoundException::new);

    currentExpense.setCategory(category);
    currentExpense.setAmount(request.getAmount());
    currentExpense.setCurrencyCode(request.getCurrencyCode());
    currentExpense.setDate(request.getDate());

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
