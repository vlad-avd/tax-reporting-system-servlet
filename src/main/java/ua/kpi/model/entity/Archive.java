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
}
