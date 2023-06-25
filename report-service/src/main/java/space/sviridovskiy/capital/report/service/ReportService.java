package space.sviridovskiy.capital.report.service;

import space.sviridovskiy.capital.report.domain.Report;

public interface ReportService {
  Report yearReport(String authorization, int year);
}
