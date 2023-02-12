package com.example.projectswp.controller;

import com.example.projectswp.model.Reports;
import com.example.projectswp.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    ReportRepository reportRepository = new ReportRepository();

    @GetMapping("/{reportID}")
    public ResponseEntity<Reports> getReport(@PathVariable int id) {
        Reports report = reportRepository.getReport(id);
        return report != null ? ResponseEntity.ok(report) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("")
    public ResponseEntity<List<Reports>> getReports() {
        List<Reports> report = reportRepository.getReports();
        return report != null ? ResponseEntity.ok(report) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PostMapping("")
    public ResponseEntity<Reports> insertReport(@RequestBody Reports addReport) {
        boolean result = reportRepository.addReport(addReport);
        URI uri = URI.create("localhost:8080/api/reports/" + reportRepository.getLastReport().getReportID());
        return result ? ResponseEntity.created(uri).build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
    @PutMapping("")
    public ResponseEntity<Reports> updateReport(@RequestBody Reports updateReport) {
        boolean result = false;
        if (reportRepository.getReport(updateReport.getReportID()) != null) {
            result = reportRepository.updateReport(updateReport);
        }
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
}

