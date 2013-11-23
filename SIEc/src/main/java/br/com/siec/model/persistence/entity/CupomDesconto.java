package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.interfaces.ICupomDesconto;
import br.com.siec.model.persistence.interfaces.IPedido;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Cupom de Desconto
 * @version 1.0.0 17 November, 2013
 * @author Josimar Alves
 */
@Entity
@Table(name="TB_CUPOMDESCONTO_CMD",schema = "siec")
public class CupomDesconto implements ICupomDesconto {
    
    @Id
    @Column(name = "CMD_CODIGO", length = 6)
    private String codigo;
    
    @Column(name = "CMD_DESCONTO", length = 2)
    private int desconto;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "CMD_INICIO")    
    private Date validadeInicial;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "CMD_FINAL")
    private Date validadeFinal; 
    
    @OneToMany(mappedBy = "cupomDeDesconto",
            targetEntity = Pedido.class, fetch = FetchType.LAZY)
    @Cascade(CascadeType.SAVE_UPDATE)    
    private List<IPedido> pedidos;
    
    
    public CupomDesconto(){
        this.pedidos = new ArrayList<IPedido>();
    }    
    
    @Override
    public String getCodigo() {
        return this.codigo;
    }

    @Override
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public int getDesconto() {
        return this.desconto;
    }

    @Override
    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }

    @Override
    public Date getValidateInicial() {
        return validadeInicial;
    }

    @Override
    public void setValidadeInicial(Date validoDe) {
        this.validadeInicial = validoDe;
    }

    @Override
    public Date getValidateFinal() {
        return this.validadeFinal;
    }

    @Override
    public void setValidadeFinal(Date validoAte) {
        this.validadeFinal = validoAte;
    }

    @Override
    public boolean isAtivo() {
       Calendar today = Calendar.getInstance();
       Calendar inicio = Calendar.getInstance();
       Calendar fim = Calendar.getInstance();
       
       today.setTime(new Date());
       inicio.setTime(validadeInicial);
       fim.setTime(validadeFinal);
       
       return (today.before(fim)) && (today.after(inicio));       
    }
    
    public void addPedido(IPedido pedido){
        this.pedidos.add(pedido);
    }
    
    public List<IPedido> getPedidos(){
        return this.pedidos;
    }
    
}
