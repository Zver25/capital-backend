package space.sviridovskiy.capital.expense.service;

import space.sviridovskiy.capital.expense.payload.CreateExpenseRequest;
import space.sviridovskiy.capital.expense.payload.UpdateExpenseRequest;
import space.sviridovskiy.capital.expense.domain.Expense;
import space.sviridovskiy.capital.expense.exeption.CategoryNotFoundException;
import space.sviridovskiy.capital.expense.exeption.ExpenseNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ExpenseService {
  List<Expense> findByPeriod(String username, LocalDate startDate, LocalDate endDate);
  Expense findById(String username, UUID id) throws ExpenseNotFoundException;
  Expense create(String username, CreateExpenseRequest createExpenseRequest) throws CategoryNotFoundException;
  Expense update(String username, UpdateExpenseRequest updateExpenseRequest) throws ExpenseNotFoundException, CategoryNotFoundException;
  void delete(String username, UUID id) throws ExpenseNotFoundException;
}
