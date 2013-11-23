package br.com.siec.business.reports.data;

import java.util.Date;

/**
 * POJO para o Relatorio de Faturamento
 * @version 1.0.0 Octuber 16, 2013.
 * @author Josimar Alves
 */
public class FaturamentoReportData {
    
    private Date data;
    
    private double valorFaturado;

    
    public FaturamentoReportData(Date data, Double valorFaturado){
        this.data = data;
        this.valorFaturado = valorFaturado;
    }
    
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValorFaturado() {
        return valorFaturado;
    }

    public void setValorFaturado(double valorFaturado) {
        this.valorFaturado = valorFaturado;
    }
}
