package space.sviridovskiy.capital.income.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import space.sviridovskiy.capital.income.domain.Category;
import space.sviridovskiy.capital.income.domain.Income;
import space.sviridovskiy.capital.income.exeption.CategoryNotFoundException;
import space.sviridovskiy.capital.income.exeption.IncomeNotFoundException;
import space.sviridovskiy.capital.income.payload.*;
import space.sviridovskiy.capital.income.repository.IncomeRepository;
import space.sviridovskiy.capital.income.service.CategoryService;
import space.sviridovskiy.capital.income.service.IncomeService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class IncomeServiceImpl implements IncomeService {
  private final IncomeRepository incomeRepository;
  private final CategoryService categoryService;

  private IncomeResponse map(Income income) {
    return IncomeResponse.builder()
      .id(income.getId())
      .categoryId(income.getCategory().getId())
      .amount(income.getAmount())
      .currencyCode(income.getCurrencyCode())
      .date(income.getDate())
      .build();
  }

  @Override
  public List<IncomeResponse> findByPeriod(String username, LocalDate startDate, LocalDate endDate) {
    return incomeRepository.findByUsernameAndDateBetween(username, startDate, endDate)
      .stream()
      .map(this::map)
      .collect(Collectors.toList());
  }

  public List<IncomeResponse> findByCategoryAndPeriod(String username, Category category, LocalDate startDate, LocalDate endDate) {
    return incomeRepository.findByUsernameAndCategoryAndDateBetween(username, category, startDate, endDate)
      .stream()
      .map(this::map)
      .collect(Collectors.toList());
  }

  @Override
  public IncomeResponse findById(String username, UUID id) throws IncomeNotFoundException {
    return incomeRepository
      .findById(id)
      .filter((e -> e.getUsername().equals(username)))
      .map(this::map)
      .orElseThrow(IncomeNotFoundException::new);
  }

  @Override
  public IncomeResponse create(String username, CreateIncomeRequest request) throws CategoryNotFoundException {
    final CategoryResponse categoryResponse = categoryService.findById(username, request.getCategoryId());
    final Category category = new Category(categoryResponse.getId());

    final Income income = new Income();
    income.setId(UUID.randomUUID());
    income.setUsername(username);
    income.setCategory(category);
    income.setAmount(request.getAmount());
    income.setCurrencyCode(request.getCurrencyCode());
    income.setDate(request.getDate());

    return map(incomeRepository.save(income));
  }

  @Override
  public IncomeResponse update(String username, UpdateIncomeRequest request) throws IncomeNotFoundException, CategoryNotFoundException {
    final CategoryResponse categoryResponse = categoryService.findById(username, request.getCategoryId());
    final Category category = new Category(categoryResponse.getId());

    final Income currentIncome = incomeRepository
      .findById(request.getId())
      .filter(e -> e.getUsername().equals(username))
      .orElseThrow(IncomeNotFoundException::new);

    currentIncome.setCategory(category);
    currentIncome.setAmount(request.getAmount());
    currentIncome.setCurrencyCode(request.getCurrencyCode());
    currentIncome.setDate(request.getDate());

    return map(incomeRepository.save(currentIncome));
  }

  @Override
  public void delete(String username, UUID id) throws IncomeNotFoundException {
    Income expense = incomeRepository
      .findById(id)
      .filter(e -> e.getUsername().equals(username))
      .orElseThrow(IncomeNotFoundException::new);

    incomeRepository.delete(expense);
  }
}
