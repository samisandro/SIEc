/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.interfaces.Composite;
import br.com.siec.model.persistence.interfaces.IComponente;
import br.com.siec.model.persistence.util.TipoPreco;
import br.com.siec.business.pricestrategy.isMultiplePrice;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author josimar
 */
@Entity
@PrimaryKeyJoinColumn(name = "CPS_CODIGO")
@Table(name = "TB_COMPOSICAO_CPS", schema = "siec")
@Audited
@AuditTable(value = "TB_COMPOSICAO_AUDIT")
public class Composicao extends Produto implements Composite {

    @ManyToMany(targetEntity = Componente.class)
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinTable(name = "TB_COMPOSICAO_COMPONENTE_ASS", joinColumns =
            @JoinColumn(name = "CPS_CODIGO"),
            inverseJoinColumns =
            @JoinColumn(name = "CPT_CODIGO"))
    private List<IComponente> componentes = new ArrayList<IComponente>();

    public Composicao() {
        super.setTypePrice(new isMultiplePrice());
    }

    @Override
    public void addPreco(Preco preco) {
        super.getTypePrice().addPrice(super.getPrecos(), preco);
    }

    @Override
    public List<Preco> getPrecos() {
        List<Preco> precos = new ArrayList<Preco>();
        Preco precoP = new Preco();
        Preco precoM = new Preco();
        Preco precoG = new Preco();
        Preco precoF = new Preco();
        
        precoP.setTipo(TipoPreco.PEQUENA);
        precoM.setTipo(TipoPreco.MEDIA);
        precoG.setTipo(TipoPreco.GRANDE);
        precoF.setTipo(TipoPreco.FAMILIA);
        
        for (IComponente componente : this.componentes) {
            
            for (Preco preco : componente.getPrecos()) {
                if (preco.getTipo().equals(TipoPreco.PEQUENA)){
                        precoP.setValor(precoP.getValor()+preco.getValor());
                }
                if (preco.getTipo().equals(TipoPreco.MEDIA)){
                        precoM.setValor(precoM.getValor()+preco.getValor());
                }
                if (preco.getTipo().equals(TipoPreco.GRANDE)){
                        precoG.setValor(precoG.getValor()+preco.getValor());
                }
                if (preco.getTipo().equals(TipoPreco.FAMILIA)){
                        precoF.setValor(precoF.getValor()+preco.getValor());
                }
            }
        }
        
        precos.add(precoP);
        precos.add(precoM);
        precos.add(precoG);
        precos.add(precoF);
        return precos;
    }

    @Override
    public List<IComponente> getProdutos() {
        return this.componentes;
    }

    @Override
    public void addComponente(IComponente componente) {
        this.componentes.add(componente);
    }

    @Override
    public void removeComponente(IComponente componente) {
        this.componentes.remove(componente);
    }

    @Override
    public IComponente getComponente(int i) {
        return this.componentes.get(i);
    }

    @Override
    public String toString() {
        return "Composicao{" + "componentes=" + componentes + '}';
    }
    
    
}
