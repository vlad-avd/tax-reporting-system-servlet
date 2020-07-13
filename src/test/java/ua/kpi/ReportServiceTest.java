package ua.kpi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.kpi.dao.impl.ReportDaoImpl;
import ua.kpi.dto.ReportDto;
import ua.kpi.service.impl.ReportServiceImpl;

import java.math.BigDecimal;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReportServiceTest {
    @Mock
    private ReportDaoImpl reportDao;

    @InjectMocks
    private ReportServiceImpl reportService;

    private ReportDto individualPersonReport;
    private ReportDto legalEntityReport;

    @Before
    public void init() {
        individualPersonReport = ReportDto.newBuilder()
                .setFullName("Vlad Avd")
                .setWorkplace("kpi")
                .setSalary(new BigDecimal("12.3"))
                .setTaxpayerId(30L)
                .setInspectorId(7L)
                .build();

        legalEntityReport = ReportDto.newBuilder()
                .setCompanyName("Taxer.ua")
                .setFinancialTurnover(new BigDecimal("44.33"))
                .setTaxpayerId(30L)
                .setInspectorId(7L)
                .build();
    }

    @Test
    public void createIndividualPersonReportTest() {
        boolean isReportCreated = reportService.createIndividualPersonReport(individualPersonReport);

        Assert.assertTrue(isReportCreated);
        verify(reportDao, times(1)).saveIndividualPersonReport(any(ReportDto.class));
    }

    @Test
    public void createLegalEntityReportTest() {
        boolean isReportCreated = reportService.createLegalEntityReport(legalEntityReport);

        Assert.assertTrue(isReportCreated);
        verify(reportDao, times(1)).saveLegalEntityReport(any(ReportDto.class));
    }
}
