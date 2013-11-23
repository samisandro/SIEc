package br.com.siec.controller;

import br.com.siec.business.reports.ReportFacade;
import br.com.siec.business.reports.core.Reports;
import br.com.siec.business.reports.data.FaturamentoReportData;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import net.sf.jasperreports.engine.JRException;

/**
 * @version 1.0.0 Octuber 16, 2013.
 * @author Josimar Alves
 */
@ManagedBean(name = "reportController")
@RequestScoped
public class ReportController implements Serializable {

    @Inject
    private Reports reportsService;
    
    @Inject
    private ReportFacade reportFacade;
    
    private Date dataInicial;
    
    private Date dataFinal;    

    public List<FaturamentoReportData> getFaturamentos() {
        return reportsService.getFaturamentoReport(new Date(), new Date());
    }

    public void generateFaturamentoReport(ActionEvent actionEvent) throws JRException, IOException {
        reportFacade.generateFaturamentoReport(dataInicial, dataFinal);
    }

    public Date getDataInicial() {
        return this.dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return this.dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }  
    
}
