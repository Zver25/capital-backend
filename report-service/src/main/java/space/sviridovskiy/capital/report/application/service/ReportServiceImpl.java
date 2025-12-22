package space.sviridovskiy.capital.report.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import space.sviridovskiy.capital.report.application.dto.ReportDto;
import space.sviridovskiy.capital.report.application.dto.TransactionDto;
import space.sviridovskiy.capital.report.application.mapper.ReportMapper;
import space.sviridovskiy.capital.report.application.mapper.TransactionMapper;
import space.sviridovskiy.capital.report.domain.model.aggregate.Report;
import space.sviridovskiy.capital.report.domain.model.entity.Transaction;
import space.sviridovskiy.capital.report.domain.service.ReportCalculationService;
import space.sviridovskiy.capital.report.infrastructure.external.ExpenseService;
import space.sviridovskiy.capital.report.infrastructure.external.IncomeService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {
  private final ExpenseService expenseService;
  private final IncomeService incomeService;
  private final ReportCalculationService calculationService;
  private final TransactionMapper transactionMapper;
  private final ReportMapper reportMapper;

  @Override
  public ReportDto yearReport(String authorization, int year) {
    LocalDate startDate = LocalDate.of(year, 1, 1);
    LocalDate endDate = LocalDate.of(year, 12, 31);

    // Fetch external data as DTOs
    List<TransactionDto> expenseDtos = expenseService.getByPeriod(
        authorization,
        startDate.toString(),
        endDate.toString()
    );
    List<TransactionDto> incomeDtos = incomeService.getByPeriod(
        authorization,
        startDate.toString(),
        endDate.toString()
    );

    // Map to domain entities
    List<Transaction> expenses = expenseDtos.stream()
        .map(transactionMapper::toDomain)
        .collect(Collectors.toList());
    List<Transaction> incomes = incomeDtos.stream()
        .map(transactionMapper::toDomain)
        .collect(Collectors.toList());

    // Use domain service for business logic
    Report report = Report.builder()
        .expense(calculationService.calculateStatistics(expenses))
        .income(calculationService.calculateStatistics(incomes))
        .year(year)
        .build();

    // Return DTO
    return reportMapper.toDto(report);
  }
}
