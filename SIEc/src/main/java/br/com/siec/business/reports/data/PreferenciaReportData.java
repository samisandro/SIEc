package br.com.siec.business.reports.data;

import java.util.Date;

/**
 * POJO para o Relatorio de Preferencia dos Clientes
 *
 * @version 1.0.0 Octuber 16, 2013.
 * @author Josimar Alves
 */
public class PreferenciaReportData {

    private String periodo;
    private long idProduto;
    private String nomeProduto;
    private double preferenciaNoPeriodo;

    public PreferenciaReportData(String periodo, Long idProduto,
            String nomeProduto, double preferenciaNoPeriodo) {
        this.periodo = periodo;
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.preferenciaNoPeriodo = preferenciaNoPeriodo;
    }
}
