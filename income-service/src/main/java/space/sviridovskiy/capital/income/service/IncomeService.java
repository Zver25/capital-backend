package space.sviridovskiy.capital.income.service;

import space.sviridovskiy.capital.income.domain.Category;
import space.sviridovskiy.capital.income.exeption.CategoryNotFoundException;
import space.sviridovskiy.capital.income.exeption.IncomeNotFoundException;
import space.sviridovskiy.capital.income.payload.CreateIncomeRequest;
import space.sviridovskiy.capital.income.payload.IncomeResponse;
import space.sviridovskiy.capital.income.payload.UpdateIncomeRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IncomeService {
  List<IncomeResponse> findByPeriod(String username, LocalDate startDate, LocalDate endDate);
  List<IncomeResponse> findByCategoryAndPeriod(String username, Category category, LocalDate startDate, LocalDate endDate);
  IncomeResponse findById(String username, UUID id) throws IncomeNotFoundException;
  IncomeResponse create(String username, CreateIncomeRequest createIncomeRequest) throws CategoryNotFoundException;
  IncomeResponse update(String username, UpdateIncomeRequest updateIncomeRequest) throws IncomeNotFoundException, CategoryNotFoundException;
  void delete(String username, UUID id) throws IncomeNotFoundException;
}
