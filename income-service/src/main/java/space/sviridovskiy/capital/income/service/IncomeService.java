package space.sviridovskiy.capital.income.service;

import space.sviridovskiy.capital.income.domain.Income;
import space.sviridovskiy.capital.income.exeption.IncomeNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IncomeService {
  List<Income> findByPeriod(String username, LocalDate startDate, LocalDate endDate);
  Income findById(String username, UUID id) throws IncomeNotFoundException;
  Income create(String username, Income expense);
  Income update(String username, Income expense) throws IncomeNotFoundException;
  void delete(String username, UUID id) throws IncomeNotFoundException;
}
