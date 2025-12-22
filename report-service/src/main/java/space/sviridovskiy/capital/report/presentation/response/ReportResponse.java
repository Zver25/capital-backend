package space.sviridovskiy.capital.report.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponse {
    private Map<String, Map<Integer, Map<String, BigDecimal>>> expense;
    private Map<String, Map<Integer, Map<String, BigDecimal>>> income;
    private int year;
}
