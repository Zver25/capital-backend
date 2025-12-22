package space.sviridovskiy.capital.report.presentation.mapper;

import org.springframework.stereotype.Component;
import space.sviridovskiy.capital.report.application.dto.ReportDto;
import space.sviridovskiy.capital.report.presentation.response.ReportResponse;

@Component
public class ReportPresentationMapper {

  public ReportResponse toResponse(ReportDto dto) {
    return ReportResponse.builder()
        .expense(dto.getExpense())
        .income(dto.getIncome())
        .year(dto.getYear())
        .build();
  }
}
