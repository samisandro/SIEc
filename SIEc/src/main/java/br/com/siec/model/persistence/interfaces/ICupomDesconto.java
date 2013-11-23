package br.com.siec.model.persistence.interfaces;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @version 1.0.0 17 November, 2013.
 * @author Josimar Alves
 */
public interface ICupomDesconto extends Serializable {

    public String getCodigo();
    
    public void setCodigo(String codigo);
    
    public int getDesconto();

    public void setDesconto(int desconto);

    public Date getValidateInicial();

    public void setValidadeInicial(Date validoDe);

    public Date getValidateFinal();

    public void setValidadeFinal(Date validoAte);

    public boolean isAtivo();
}
