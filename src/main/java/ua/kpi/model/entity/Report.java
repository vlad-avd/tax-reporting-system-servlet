package ua.kpi.model.entity;

import ua.kpi.model.enums.PersonType;
import ua.kpi.model.enums.RejectionReason;
import ua.kpi.model.enums.ReportStatus;
import ua.kpi.model.enums.Role;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Report {
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

    private Report(){

    }

    public Builder getBuilder() {
        return new Builder();
    }

    public static Builder newBuilder() {
        return new Report().new Builder();
    }

    public class Builder {
        private Builder() {}

        public Report build() {
            return Report.this;
        }

        public Builder setId(Long id) {
            Report.this.id = id;
            return this;
        }

        public Builder setFullName(String fullName) {
            Report.this.fullName = fullName;
            return this;
        }

        public Builder setWorkplace(String workplace) {
            Report.this.workplace = workplace;
            return this;
        }

        public Builder setSalary(BigDecimal salary) {
            Report.this.salary = salary;
            return this;
        }

        public Builder setCompanyName(String companyName) {
            Report.this.companyName = companyName;
            return this;
        }

        public Builder setFinancialTurnover(BigDecimal financialTurnover) {
            Report.this.financialTurnover = financialTurnover;
            return this;
        }

        public Builder setPersonType(PersonType personType) {
            Report.this.personType = personType;
            return this;
        }

        public Builder setReportStatus(ReportStatus reportStatus) {
            Report.this.reportStatus = reportStatus;
            return this;
        }

    }
}
