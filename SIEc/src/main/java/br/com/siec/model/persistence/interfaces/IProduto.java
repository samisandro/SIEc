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
package br.com.siec.model.persistence.interfaces;

import br.com.siec.model.persistence.entity.Imagem;
import br.com.siec.model.persistence.entity.Preco;
import br.com.siec.model.persistence.resource.Categorias;
import br.com.siec.business.price_strategy.MultiplePrice;
import br.com.siec.model.persistence.entity.Item;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * IProduto
 * @version 1.00 May 21, 2013.
 * @author Josimar Alves
 */
public interface IProduto extends Serializable{

    public long getId();

    public void setId(long id);

    public String getNome();

    public void setNome(String nome);

    public Categorias getCategoria();

    public void setCategoria(Categorias categoria);

    public Imagem getImagem();

    public void setImagem(Imagem imagem);

    public void addItem(Item item);

    public Set<Item> getItens();

    public void addPreco(Preco preco);

    public List<Preco> getPrecos();

    public MultiplePrice getTypePrice();

    public void setTypePrice(MultiplePrice typePrice);
}
