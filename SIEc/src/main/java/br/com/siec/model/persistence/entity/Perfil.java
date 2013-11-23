package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.interfaces.ICliente;
import br.com.siec.model.persistence.interfaces.IPerfil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author josimar
 */
@Entity
@Table(name="PRF_PERFIL_TB", schema="siec")
public class Perfil implements IPerfil {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRF_CODIGO")
    private Long id;
    
    @OneToOne(targetEntity = Cliente.class)
    private ICliente cliente;
    
    @ManyToMany(mappedBy = "sugestoes", targetEntity = Produto.class)
    private Collection<Produto> sugestoes = new ArrayList<Produto>();
    
    @ManyToMany(mappedBy = "preferencias", targetEntity = Produto.class)
    private Collection<Produto> preferencias = new ArrayList<Produto>();

    public Perfil() {
    }
            
    public Perfil(ICliente cliente) {
        this.cliente = cliente;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }  
    
    @Override
    public Collection<Produto> getPreferencias() {
        return this.preferencias;
    }

    @Override
    public Collection<Produto> getSugestoes() {
        return this.sugestoes;
    }

    @Override
    public void addPreferencia(Produto preferencia) {
        this.preferencias.add(preferencia);
    }

    @Override
    public void addSugestao(Produto sugestao) {
        this.preferencias.add(sugestao);
    }

    @Override
    public void serCliente(ICliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public ICliente getCliente() {
        return this.cliente;
    }
    
}
