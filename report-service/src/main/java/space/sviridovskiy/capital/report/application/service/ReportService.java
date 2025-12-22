package space.sviridovskiy.capital.report.application.service;

import space.sviridovskiy.capital.report.application.dto.ReportDto;

public interface ReportService {
  ReportDto yearReport(String authorization, int year);
}
