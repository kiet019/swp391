package com.example.projectswp.repositories.rowMapper;

import com.example.projectswp.model.Items;
import com.example.projectswp.model.Reports;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportsRowMapper implements RowMapper<Reports> {
    @Override
    public Reports mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reports report = new Reports();

        report.setReportID(rs.getInt("ReportID"));
        report.setItemID(rs.getInt("ItemID"));
        report.setReportDateCreate(rs.getDate("Report_Date_Create"));
        report.setReportDateUpdate(rs.getDate("Report_Date_Update"));
        report.setReportStatus(rs.getBoolean("Report_Status"));
        report.setReportContent(rs.getString("Report_Content"));
        return report;
    }

}
