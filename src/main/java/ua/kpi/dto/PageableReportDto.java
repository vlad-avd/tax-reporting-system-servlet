package ua.kpi.dto;

import ua.kpi.model.entity.Report;

import java.util.ArrayList;
import java.util.List;

public class PageableReportDto {
    private List<Report> reportsPage;
    private int rowNumber;

    public PageableReportDto() {
        reportsPage = new ArrayList<>();
    }

    public List<Report> getReportsPage() {
        return reportsPage;
    }

    public void setReportsPage(List<Report> reportsPage) {
        this.reportsPage = reportsPage;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }
}
