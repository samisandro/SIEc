package br.com.siec.business.reports.core;

import br.com.siec.business.reports.data.FaturamentoReportData;
import br.com.siec.business.reports.data.FrequenciaReportData;
import br.com.siec.business.reports.data.PreferenciaReportData;
import br.com.siec.model.dao.core.DAO;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;


/**
 *
 * @author Josimar Alves
 */
public class ReportsImpl
        extends DAO<Object> implements Reports {
    
    @Override
    public List<FaturamentoReportData> getFaturamentoReport(Date inicial, Date finall) {
        
        Query query = super.getEntityManager().createQuery(getFaturamentoQuery());
        query.setParameter("dataInicial", inicial);
        query.setParameter("dataFinal", finall);
        
        return query.getResultList();        
    }
    
    private String getFaturamentoQuery() {
        StringBuilder query = new StringBuilder();
        query.append("   SELECT NEW br.com.siec.business.reports.data.FaturamentoReportData( p.dataCompra, sum(p.valorTotal) ) ")
                .append("  FROM Pedido p ")
                .append(" WHERE p.dataCompra BETWEEN :dataInicial AND :dataFinal ")
                .append(" GROUP BY p.dataCompra ")
                .append(" ORDER BY p.dataCompra ASC ");
        
        return query.toString();        
    }
    
    @Override
    public List<PreferenciaReportData> getPreferenciaReport(Date inicial, Date finall){
        return null;
    	
    }
    
    private String getPreferenciaQuery() {
        StringBuilder query = new StringBuilder();
        query.append("   SELECT NEW br.com.siec.business.reports.data.PreferenciaReportData( p.dataCompra, sum(p.valorTotal) ) ")
                .append("  FROM Pedido p ")
                .append(" WHERE p.dataCompra BETWEEN :dataInicial AND :dataFinal ")
                .append(" GROUP BY p.dataCompra ")
                .append(" ORDER BY p.dataCompra ASC ");
        
        return query.toString();        
    }
    
    @Override
    public List<FrequenciaReportData> getFrequenciaReport(Date inicial, Date finall){
        return null;
    	
    }
    
    private String getFrequenciaQuery() {
        StringBuilder query = new StringBuilder();
        query.append("   SELECT NEW br.com.siec.business.reports.data.FrequenciaReportData( p.dataCompra, sum(p.valorTotal) ) ")
                .append("  FROM Pedido p ")
                .append(" WHERE p.dataCompra BETWEEN :dataInicial AND :dataFinal ")
                .append(" GROUP BY p.dataCompra ")
                .append(" ORDER BY p.dataCompra ASC ");
        
        return query.toString();        
    }
}
