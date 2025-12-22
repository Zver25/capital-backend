package space.sviridovskiy.capital.report.presentation.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.sviridovskiy.capital.report.application.dto.ReportDto;
import space.sviridovskiy.capital.report.application.service.ReportService;
import space.sviridovskiy.capital.report.presentation.mapper.ReportPresentationMapper;
import space.sviridovskiy.capital.report.presentation.response.ReportResponse;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/report")
public class ReportController {
  private final ReportService reportService;
  private final ReportPresentationMapper presentationMapper;

  @GetMapping("{year}")
  public ResponseEntity<ReportResponse> getById(
    @PathVariable int year,
    @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
  ) {
    ReportDto reportDto = reportService.yearReport(authorization, year);
    ReportResponse response = presentationMapper.toResponse(reportDto);
    return ResponseEntity.ok(response);
  }
}
