package space.sviridovskiy.capital.report.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.sviridovskiy.capital.report.service.ReportService;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/report")
public class ReportController {
  private final ReportService reportService;

  @GetMapping("{year}")
  public ResponseEntity<?> getById(
    @PathVariable int year,
    @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
  ) {
    return ResponseEntity.ok(
      reportService.yearReport(authorization, year)
    );
  }
}
