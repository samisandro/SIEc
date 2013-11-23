package br.com.siec.business.reports;

import br.com.siec.business.reports.core.Reports;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @version 1.0.0 Octuber 16, 2013.
 * @author Josimar Alves
 */
public class ReportFacade {
    
    @Inject
    private Reports reports;
    
    @Inject
    private HttpServletResponse response;
    
    @Inject
    private ServletContext servletContext;
    
    public void generateFaturamentoReport(Date inicio, Date finall) throws JRException, IOException{
        String pathReport = servletContext.getRealPath("/WEB-INF/Reports/")+"/";
        
        ServletOutputStream responseStream = response.getOutputStream();
              
        Collection data = reports.getFaturamentoReport(inicio, finall);      

        response.setContentType("application/pdf");

        response.setHeader("Content-Disposition","attachment; filename=\'relatorio.pdf\'");

        JasperPrint preencher = JasperFillManager.fillReport(pathReport + "FaturamentoReport.jasper", null,new JRBeanCollectionDataSource(data));

        JasperExportManager.exportReportToPdfStream(preencher,responseStream);

        responseStream.flush();

        responseStream.close();
//
//        servletContext.renderResponse();
//
//        servletContext.responseComplete();        
        
    }

}
