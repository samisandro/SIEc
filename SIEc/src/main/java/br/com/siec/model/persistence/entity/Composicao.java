/*
 * %W% %E% Josimar Alves
 *
 * Copyright (c) 2013-2014 Josimar Alves, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Josimar Alves. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with JOSIMAR ALVES.
 *
 * JOSIMAR ALVES MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. JOSIMAR ALVES SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */
package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.interfaces.Composite;
import br.com.siec.model.persistence.interfaces.IComponente;

import br.com.siec.model.persistence.resource.TipoPreco;

import br.com.siec.business.price_strategy.isMultiplePrice;
import br.com.siec.model.persistence.resource.Categorias;

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

/**
 * <b> Composição </b> 
 * @version 1.00 May 21, 2013.
 * @author Josimar Alves
 */
@Entity
@PrimaryKeyJoinColumn(name = "CPS_CODIGO")
@Table(name = "TB_COMPOSICAO_CPS", schema = "siec")
public class Composicao
        extends Produto implements Composite {

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

        if (!(getComponentes() == null)) {
            for (IComponente componente : getComponentes()) {

                for (Preco preco : componente.getPrecos()) {
                    if (preco.getTipo().equals(TipoPreco.PEQUENA)) {
                        precoP.setValor(precoP.getValor() + preco.getValor());
                    }
                    if (preco.getTipo().equals(TipoPreco.MEDIA)) {
                        precoM.setValor(precoM.getValor() + preco.getValor());
                    }
                    if (preco.getTipo().equals(TipoPreco.GRANDE)) {
                        precoG.setValor(precoG.getValor() + preco.getValor());
                    }
                    if (preco.getTipo().equals(TipoPreco.FAMILIA)) {
                        precoF.setValor(precoF.getValor() + preco.getValor());
                    }
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
    public List<IComponente> getComponentes() {
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
        return "Composicao{ id=" + getId() + "componentes=" + componentes + '}';
    }

    @Override
    public void removeByCategory(Categorias categoria) {
        for (int i = 0; i < this.componentes.size(); i++) {
            if (this.componentes.get(i).getCategoria().equals(categoria)) {
                this.componentes.remove(i);
                --i;
            }
        }
    }
}
