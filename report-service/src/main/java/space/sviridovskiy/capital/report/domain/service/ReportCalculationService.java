package space.sviridovskiy.capital.report.domain.service;

import org.springframework.stereotype.Service;
import space.sviridovskiy.capital.report.domain.model.entity.Transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ReportCalculationService {

  public Map<String, Map<Integer, Map<String, BigDecimal>>> calculateStatistics(
      List<Transaction> transactions
  ) {
    return transactions.stream()
        .collect(groupByCategory());
  }

  private Collector<Transaction, ?, Map<String, Map<Integer, Map<String, BigDecimal>>>> groupByCategory() {
    return Collectors.groupingBy(
        t -> t.getCategoryId().getValue(),
        groupByMonth()
    );
  }

  private Collector<Transaction, ?, Map<Integer, Map<String, BigDecimal>>> groupByMonth() {
    return Collectors.groupingBy(
        Transaction::getMonth,
        groupByCurrency()
    );
  }

  private Collector<Transaction, ?, Map<String, BigDecimal>> groupByCurrency() {
    return Collectors.groupingBy(
        t -> t.getAmount().getCurrencyCode().getValue(),
        sumAmounts()
    );
  }

  private Collector<Transaction, ?, BigDecimal> sumAmounts() {
    return Collectors.mapping(
        t -> t.getAmount().getValue(),
        Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
    );
  }
}
