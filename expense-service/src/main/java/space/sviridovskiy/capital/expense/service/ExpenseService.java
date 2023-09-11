package space.sviridovskiy.capital.expense.service;

import space.sviridovskiy.capital.expense.domain.Category;
import space.sviridovskiy.capital.expense.payload.CreateExpenseRequest;
import space.sviridovskiy.capital.expense.payload.ExpenseResponse;
import space.sviridovskiy.capital.expense.payload.UpdateExpenseRequest;
import space.sviridovskiy.capital.expense.exeption.CategoryNotFoundException;
import space.sviridovskiy.capital.expense.exeption.ExpenseNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ExpenseService {
  List<ExpenseResponse> findByPeriod(String username, LocalDate startDate, LocalDate endDate);
  List<ExpenseResponse> findByCategoryAndPeriod(String username, Category category, LocalDate startDate, LocalDate endDate);
  ExpenseResponse findById(String username, UUID id) throws ExpenseNotFoundException;
  ExpenseResponse create(String username, CreateExpenseRequest createExpenseRequest) throws CategoryNotFoundException;
  ExpenseResponse update(String username, UpdateExpenseRequest updateExpenseRequest) throws ExpenseNotFoundException, CategoryNotFoundException;
  void delete(String username, UUID id) throws ExpenseNotFoundException;
}
