package ua.kpi.dto;

import java.time.LocalDate;

public class StatisticsDto {
    private int reportsNumber;

    private LocalDate firstReportDate;

    private LocalDate lastReportDate;

    private int onVerifyingReportsNumber;

    private int needToEditReportsNumber;

    private int approvedReportsNumber;

    private int rejectedReportsNumber;

    public int getReportsNumber() {
        return reportsNumber;
    }

    public LocalDate getFirstReportDate() {
        return firstReportDate;
    }

    public LocalDate getLastReportDate() {
        return lastReportDate;
    }

    public int getOnVerifyingReportNumber() {
        return onVerifyingReportsNumber;
    }

    public int getNeedToEditReportNumber() {
        return needToEditReportsNumber;
    }

    public int getApprovedReportNumber() {
        return approvedReportsNumber;
    }

    public int getRejectedReportNumber() {
        return rejectedReportsNumber;
    }

    private StatisticsDto(){};

    public Builder getBuilder() {
        return new Builder();
    }

    public static StatisticsDto.Builder newBuilder() {
        return new StatisticsDto().new Builder();
    }

    public class Builder {
        private Builder() {}

        public StatisticsDto build() {
            return StatisticsDto.this;
        }

        public StatisticsDto.Builder setReportsNumber(int reportsNumber) {
            StatisticsDto.this.reportsNumber = reportsNumber;
            return this;
        }

        public StatisticsDto.Builder setFirstReportDate(LocalDate firstReportDate) {
            StatisticsDto.this.firstReportDate = firstReportDate;
            return this;
        }

        public StatisticsDto.Builder setLastReportDate(LocalDate lastReportDate) {
            StatisticsDto.this.lastReportDate = lastReportDate;
            return this;
        }

        public StatisticsDto.Builder setOnVerifyingReportsNumber(int onVerifyingReportsNumber) {
            StatisticsDto.this.onVerifyingReportsNumber = onVerifyingReportsNumber;
            return this;
        }

        public StatisticsDto.Builder setNeedToEditReportsNumber(int needToEditReportsNumber) {
            StatisticsDto.this.needToEditReportsNumber = needToEditReportsNumber;
            return this;
        }

        public StatisticsDto.Builder setApprovedReportsNumber(int approvedReportsNumber) {
            StatisticsDto.this.approvedReportsNumber = approvedReportsNumber;
            return this;
        }

        public StatisticsDto.Builder setRejectedReportsNumber(int rejectedReportsNumber) {
            StatisticsDto.this.rejectedReportsNumber = rejectedReportsNumber;
            return this;
        }
    }
}
