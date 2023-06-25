package space.sviridovskiy.capital.report.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import space.sviridovskiy.capital.report.domain.Report;
import space.sviridovskiy.capital.report.domain.Transaction;
import space.sviridovskiy.capital.report.service.ExpenseService;
import space.sviridovskiy.capital.report.service.IncomeService;
import space.sviridovskiy.capital.report.service.ReportService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {
  private final ExpenseService expenseService;
  private final IncomeService incomeService;


  @Override
  public Report yearReport(String authorization, int year) {
    LocalDate startDate = LocalDate.of(year, 1, 1);
    LocalDate endDate = LocalDate.of(year, 12, 31);

    final List<Transaction> expenses = expenseService.getByPeriod(authorization, startDate.toString(), endDate.toString());
    final List<Transaction> incomes = incomeService.getByPeriod(authorization, startDate.toString(), endDate.toString());

    return Report.builder()
      .expense(calculateYearStatistic(expenses))
      .income(calculateYearStatistic(incomes))
      .build();
  }

  private Map<String, Map<Integer, Map<String, BigDecimal>>> calculateYearStatistic(
    List<Transaction> transactions
  ) {
    return transactions
      .stream()
      .collect(
        getTransactionCategory()
      );
  }

  private static Collector<Transaction, ?, Map<String, Map<Integer, Map<String, BigDecimal>>>> getTransactionCategory() {
    return Collectors.groupingBy(
      Transaction::getCategoryId,
      getTransactionMonth()
    );
  }

  private static Collector<Transaction, ?, Map<Integer, Map<String, BigDecimal>>> getTransactionMonth() {
    return Collectors.groupingBy(
      Transaction::getMonth,
      getCurrency()
    );
  }


  private static Collector<Transaction, ?, Map<String, BigDecimal>> getCurrency() {
    return Collectors.groupingBy(
      Transaction::getCurrencyCode,
      getSum()
    );
  }

  private static Collector<Transaction, ?, BigDecimal> getSum() {
    return Collectors.mapping(
      Transaction::getAmount,
      Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
    );
  }
}
