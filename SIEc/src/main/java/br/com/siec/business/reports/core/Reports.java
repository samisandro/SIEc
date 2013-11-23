package br.com.siec.business.reports.core;

import br.com.siec.business.reports.data.FaturamentoReportData;
import br.com.siec.business.reports.data.FrequenciaReportData;
import br.com.siec.business.reports.data.PreferenciaReportData;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Josimar Alves
 */
public interface Reports {
    
    public List<FaturamentoReportData> getFaturamentoReport(Date inicial, Date finall);
    
    public List<PreferenciaReportData> getPreferenciaReport(Date inicial, Date finall);
    
    public List<FrequenciaReportData> getFrequenciaReport(Date inicial, Date finall);
    
}
