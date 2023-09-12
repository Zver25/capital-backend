package space.sviridovskiy.capital.expense.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import space.sviridovskiy.capital.expense.payload.CategoryResponse;
import space.sviridovskiy.capital.expense.payload.CreateExpenseRequest;
import space.sviridovskiy.capital.expense.payload.ExpenseResponse;
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
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {
  private final ExpenseRepository expenseRepository;
  private final CategoryService categoryService;

  private ExpenseResponse map(Expense expense) {
    return ExpenseResponse.builder()
      .id(expense.getId())
      .categoryId(expense.getCategory().getId())
      .amount(expense.getAmount())
      .currencyCode(expense.getCurrencyCode())
      .date(expense.getDate())
      .build();
  }

  @Override
  public List<ExpenseResponse> findByPeriod(String username, LocalDate startDate, LocalDate endDate) {
    return expenseRepository.findByUsernameAndDateBetween(username, startDate, endDate)
      .stream()
      .map(this::map)
      .collect(Collectors.toList());
  }

  @Override
  public List<ExpenseResponse> findByCategoryAndPeriod(String username, Category category, LocalDate startDate, LocalDate endDate) {
    return expenseRepository.findByUsernameAndCategoryAndDateBetween(username, category, startDate, endDate)
      .stream()
      .map(this::map)
      .collect(Collectors.toList());
  }

  @Override
  public ExpenseResponse findById(String username, UUID id) throws ExpenseNotFoundException {
    return expenseRepository
      .findById(id)
      .filter((e -> e.getUsername().equals(username)))
      .map(this::map)
      .orElseThrow(ExpenseNotFoundException::new);
  }

  @Override
  public ExpenseResponse create(String username, CreateExpenseRequest request) throws CategoryNotFoundException {
    final CategoryResponse categoryResponse = categoryService.findById(username, request.getCategoryId());
    final Category category = new Category(categoryResponse.getId());

    final Expense expense = new Expense();
    expense.setId(UUID.randomUUID());
    expense.setUsername(username);
    expense.setCategory(category);
    expense.setAmount(request.getAmount());
    expense.setCurrencyCode(request.getCurrencyCode());
    expense.setDate(request.getDate());

    return map(expenseRepository.save(expense));
  }

  @Override
  public ExpenseResponse update(String username, UpdateExpenseRequest request) throws ExpenseNotFoundException, CategoryNotFoundException {
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

    return map(expenseRepository.save(currentExpense));
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
