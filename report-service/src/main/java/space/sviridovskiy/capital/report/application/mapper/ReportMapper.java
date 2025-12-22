package space.sviridovskiy.capital.report.application.mapper;

import org.springframework.stereotype.Component;
import space.sviridovskiy.capital.report.application.dto.ReportDto;
import space.sviridovskiy.capital.report.domain.model.aggregate.Report;

@Component
public class ReportMapper {

  public ReportDto toDto(Report domain) {
    return ReportDto.builder()
        .expense(domain.getExpense())
        .income(domain.getIncome())
        .year(domain.getYear())
        .build();
  }

  public Report toDomain(ReportDto dto) {
    return Report.builder()
        .expense(dto.getExpense())
        .income(dto.getIncome())
        .year(dto.getYear())
        .build();
  }
}
