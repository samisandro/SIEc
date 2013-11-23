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

import br.com.siec.business.price_strategy.isMultiplePrice;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import javax.persistence.PrimaryKeyJoinColumn;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Componente
 *
 * @version 1.00 May 21, 2013.
 * @author Josimar Alves
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@PrimaryKeyJoinColumn(name = "CPT_CODIGO")
@Table(name = "TB_COMPONENTE_CPT", schema = "siec")
public class Componente
        extends Produto implements IComponente {

    @ManyToMany(targetEntity = Composicao.class, mappedBy = "componentes")
    private List<Composite> composicoes = new ArrayList<Composite>();

    public Componente() {
        super.setTypePrice(new isMultiplePrice());
    }

    @Override
    public void addPreco(Preco preco) {
        super.getTypePrice().addPrice(super.getPrecos(), preco);
    }

    @Override
    public List<Composite> getComposicoes() {
        return this.composicoes;
    }

    @Override
    public void addComposicao(Composite composicao) {
        this.composicoes.add(composicao);
    }

    @Override
    public String toString() {
        return getNome();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (this.composicoes != null ? this.composicoes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Componente other = (Componente) obj;
        if (this.composicoes != other.composicoes && (this.composicoes == null || !this.composicoes.equals(other.composicoes))) {
            return false;
        }
        return true;
    }
}
