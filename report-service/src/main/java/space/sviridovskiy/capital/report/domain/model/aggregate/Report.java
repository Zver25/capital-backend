package space.sviridovskiy.capital.report.domain.model.aggregate;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Builder
public class Report {
    private final Map<String, Map<Integer, Map<String, BigDecimal>>> expense;
    private final Map<String, Map<Integer, Map<String, BigDecimal>>> income;
    private final int year;

    public BigDecimal getTotalExpenseForCategory(String categoryId) {
        return expense.getOrDefault(categoryId, Map.of())
            .values()
            .stream()
            .flatMap(monthMap -> monthMap.values().stream())
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalIncomeForCategory(String categoryId) {
        return income.getOrDefault(categoryId, Map.of())
            .values()
            .stream()
            .flatMap(monthMap -> monthMap.values().stream())
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
