package space.sviridovskiy.capital.report.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
public class Report {
    private Map<String, Map<Integer, Map<String, BigDecimal>>> expense;
    private Map<String, Map<Integer, Map<String, BigDecimal>>> income;
}
