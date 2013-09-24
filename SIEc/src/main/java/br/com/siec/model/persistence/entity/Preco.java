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

import br.com.siec.model.persistence.interfaces.IPreco;
import br.com.siec.model.persistence.resource.TipoPreco;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Preco
 *
 * @version 1.00 May 21, 2013.
 * @author Josimar Alves
 */
@Embeddable
public class Preco implements IPreco {

    @Enumerated(EnumType.STRING)
    @Column(name = "PRC_TIPO")
    private TipoPreco tipo;
    @Column(name = "PRC_VALOR")
    private double valor;

    public Preco() {
    }

    public Preco(TipoPreco tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    @Override
    public TipoPreco getTipo() {
        return this.tipo;
    }

    @Override
    public void setTipo(TipoPreco tipo) {
        this.tipo = tipo;
    }

    @Override
    public double getValor() {
        return this.valor;
    }

    @Override
    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Preco{" + "tipo=" + tipo + ", valor=" + valor + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.tipo != null ? this.tipo.hashCode() : 0);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
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
        final Preco other = (Preco) obj;
        if (this.tipo != other.tipo) {
            return false;
        }
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        return true;
    }
}
