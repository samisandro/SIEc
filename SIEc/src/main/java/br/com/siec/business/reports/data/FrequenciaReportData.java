package br.com.siec.business.reports.data;

import java.util.Date;

/**
 * POJO para o Relatorio de Frequencia de
 * Compra dos Clientes
 * @version 1.0.0 Octuber 16, 2013.
 * @author Josimar Alves
 */
public class FrequenciaReportData {
    
    private long idCliente;
    
    private String nomeCliente;
    
    private Date dataUltimaCompra;
    
    private double valorUltimaCompra;
    
    private int quantidadeComprasRealizadas;
    
    private double frequenciaCompra;
    
    public FrequenciaReportData(Long idCliente, String nomeCliente, 
    				Date dataUltimaCompra,	double valorUltimaCompra, 
    				int quantidadeComprasRealizadas, double frequenciaCompra){
    	this.idCliente = idCliente;
    	this.nomeCliente = nomeCliente;
        this.dataUltimaCompra = dataUltimaCompra;
        this.valorUltimaCompra = valorUltimaCompra;
        this.quantidadeComprasRealizadas = quantidadeComprasRealizadas;
        this.frequenciaCompra = frequenciaCompra;
    }
    

}
