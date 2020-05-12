package ua.kpi.dto;

import ua.kpi.model.entity.Report;
import ua.kpi.model.enums.PersonType;
import ua.kpi.model.enums.RejectionReason;
import ua.kpi.model.enums.ReportStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReportDto {
    private Long id;

    private String fullName;
    private String workplace;
    private BigDecimal salary;

    private String companyName;
    private BigDecimal financialTurnover;

    private PersonType personType;
    private ReportStatus reportStatus;
    private RejectionReason rejectionReason;
    private String comment;

    private LocalDate created;
    private LocalDate lastEdit;

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getWorkplace() {
        return workplace;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getCompanyName() {
        return companyName;
    }

    public BigDecimal getFinancialTurnover() {
        return financialTurnover;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public ReportStatus getReportStatus() {
        return reportStatus;
    }

    public RejectionReason getRejectionReason() {
        return rejectionReason;
    }

    public String getComment() {
        return comment;
    }

    public LocalDate getCreated() {
        return created;
    }

    public LocalDate getLastEdit() {
        return lastEdit;
    }

    private ReportDto(){

    }

    public ReportDto.Builder getBuilder() {
        return new ReportDto.Builder();
    }

    public static ReportDto.Builder newBuilder() {
        return new ReportDto().new Builder();
    }

    public class Builder {
        private Builder() {}

        public ReportDto build() {
            return ReportDto.this;
        }

        public ReportDto.Builder setId(Long id) {
            ReportDto.this.id = id;
            return this;
        }

        public ReportDto.Builder setFullName(String fullName) {
            ReportDto.this.fullName = fullName;
            return this;
        }

        public ReportDto.Builder setWorkplace(String workplace) {
            ReportDto.this.workplace = workplace;
            return this;
        }

        public ReportDto.Builder setSalary(BigDecimal salary) {
            ReportDto.this.salary = salary;
            return this;
        }

        public ReportDto.Builder setCompanyName(String companyName) {
            ReportDto.this.companyName = companyName;
            return this;
        }

        public ReportDto.Builder setFinancialTurnover(BigDecimal financialTurnover) {
            ReportDto.this.financialTurnover = financialTurnover;
            return this;
        }

        public ReportDto.Builder setPersonType(PersonType personType) {
            ReportDto.this.personType = personType;
            return this;
        }

        public ReportDto.Builder setReportStatus(ReportStatus reportStatus) {
            ReportDto.this.reportStatus = reportStatus;
            return this;
        }

    }
}
