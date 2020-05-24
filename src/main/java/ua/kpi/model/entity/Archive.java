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

    private Long taxpayerId;
    private Long inspectorId;

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

    public Long getTaxpayerId() {
        return taxpayerId;
    }

    public Long getInspectorId() {
        return inspectorId;
    }

    public LocalDate getCreated() {
        return created;
    }

    public LocalDate getLastEdit() {
        return lastEdit;
    }

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

        public Archive.Builder setId(Long id) {
            Archive.this.id = id;
            return this;
        }

        public Archive.Builder setFullName(String fullName) {
            Archive.this.fullName = fullName;
            return this;
        }

        public Archive.Builder setWorkplace(String workplace) {
            Archive.this.workplace = workplace;
            return this;
        }

        public Archive.Builder setSalary(BigDecimal salary) {
            Archive.this.salary = salary;
            return this;
        }

        public Archive.Builder setCompanyName(String companyName) {
            Archive.this.companyName = companyName;
            return this;
        }

        public Archive.Builder setFinancialTurnover(BigDecimal financialTurnover) {
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

        public Archive.Builder setTaxpayerId(Long taxpayerId) {
            Archive.this.taxpayerId = taxpayerId;
            return this;
        }

        public Archive.Builder setInspectorId(Long inspectorId) {
            Archive.this.inspectorId = inspectorId;
            return this;
        }

        public Archive.Builder setRejectionReason(RejectionReason rejectionReason) {
            Archive.this.rejectionReason = rejectionReason;
            return this;
        }

        public Archive.Builder setComment(String comment) {
            Archive.this.comment = comment;
            return this;
        }

        public Archive.Builder setCreated(LocalDate created) {
            Archive.this.created = created;
            return this;
        }

        public Archive.Builder setLastEdit(LocalDate lastEdit) {
            Archive.this.lastEdit = lastEdit;
            return this;
        }


    }
}
