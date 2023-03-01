package com.example.projectswp.controller;

import com.example.projectswp.model.Reports;
import com.example.projectswp.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ReportController {
    @Autowired
    ReportRepository reportRepository;

    @PostMapping("/CreateReport")
    public ResponseEntity<Reports> createReport(@RequestBody Reports addReport) {
        boolean result = reportRepository.addReport(addReport);
        //URI uri = URI.create("localhost:8080/api/reports/" + reportRepository.getLastReport().getReportID());
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PutMapping("/UpdateReport")
    public ResponseEntity<Reports> updateReport(@RequestBody Reports report) {
        boolean result = reportRepository.updateReport(report);
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PatchMapping("/DeleteReport")
    public ResponseEntity<Reports> deleteReport(@RequestBody int reportID, @RequestBody int isApproved) {
        boolean result = reportRepository.updateReportStatus(reportID);
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

