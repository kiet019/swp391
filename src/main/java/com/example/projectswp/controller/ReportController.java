package com.example.projectswp.controller;

import com.example.projectswp.data_view_model.blogcategory.ReturnMessage;
import com.example.projectswp.data_view_model.report.ApprovedReportVM;
import com.example.projectswp.data_view_model.report.CreateReportVM;
import com.example.projectswp.data_view_model.report.DenyReportVM;
import com.example.projectswp.data_view_model.report.UpdateReportVM;
import com.example.projectswp.model.Reports;
import com.example.projectswp.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/Report")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ReportController {
    @Autowired
    ReportRepository reportRepository;

    @PostMapping("/CreateReport")
    public ResponseEntity<Reports> createReport(@RequestBody CreateReportVM createReportVM) {
        boolean result = reportRepository.addReport(createReportVM);
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PutMapping("/UpdateReport")
    public ResponseEntity<Reports> updateReport(@RequestBody UpdateReportVM updateReportVM) {
        boolean result = reportRepository.updateReport(updateReportVM);
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/ApprovedReport")
    public ResponseEntity<Object> approvedReport(@RequestBody ApprovedReportVM approvedReportVM) {
        boolean result = reportRepository.updateReportStatus(approvedReportVM.getReportID(), approvedReportVM.getIsApproved());
        return result ? ResponseEntity.ok(ReturnMessage.create("approve success")) : ResponseEntity.notFound().build();
    }
    @PatchMapping("/DenyReport")
    public ResponseEntity<Object> denyReport(@RequestBody DenyReportVM denyReportVM) {
        boolean result = reportRepository.updateReportStatus(denyReportVM.getReportID(), denyReportVM.getIsDeny());
        return result ? ResponseEntity.ok(ReturnMessage.create("deny success")) : ResponseEntity.notFound().build();
    }
    @GetMapping("/GetReport/Status")
    public ResponseEntity<Object> getStatus(@RequestParam int status) {
       List<Reports> list = reportRepository.getReportByStatus(status);
       return (list != null && list.size() != 0)? ResponseEntity.ok(list) : ResponseEntity.notFound().build();
    }
}

