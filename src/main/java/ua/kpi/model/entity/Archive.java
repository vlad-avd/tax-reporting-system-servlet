package ua.kpi.model.entity;

import ua.kpi.model.enums.PersonType;
import ua.kpi.model.enums.RejectionReason;
import ua.kpi.model.enums.ReportStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Archive {
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

    private Archive(){

    }

    public Builder getBuilder() {
        return new Builder();
    }

    public static Builder newBuilder() {
        return new Archive().new Builder();
    }

    public class Builder {
        private Builder() {}

        public Archive build() {
            return Archive.this;
        }

        public Builder setId(Long id) {
            Archive.this.id = id;
            return this;
        }

        public Builder setFullName(String fullName) {
            Archive.this.fullName = fullName;
            return this;
        }

        public Builder setWorkplace(String workplace) {
            Archive.this.workplace = workplace;
            return this;
        }

        public Builder setSalary(BigDecimal salary) {
            Archive.this.salary = salary;
            return this;
        }

        public Builder setCompanyName(String companyName) {
            Archive.this.companyName = companyName;
            return this;
        }

        public Builder setFinancialTurnover(BigDecimal financialTurnover) {
            Archive.this.financialTurnover = financialTurnover;
            return this;
        }

        public Archive.Builder setPersonType(PersonType personType) {
            Archive.this.personType = personType;
            return this;
        }

        public Archive.Builder setReportStatus(ReportStatus reportStatus) {
            Archive.this.reportStatus = reportStatus;
            return this;
        }

    }
}
