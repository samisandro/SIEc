/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.business.pricestrategy;

import br.com.siec.model.persistence.entity.Preco;
import java.util.List;

/**
 *
 * @author josimar
 */
public class isMultiplePrice implements MultiplePrice {
    
    private final boolean MULTIPLE_PRICE = true;

    @Override
    public List<Preco> addPrice(List<Preco> prices, Preco preco){
        prices.add(preco);
        return prices;
    }

    @Override
    public boolean isMultiplePrice() {
        return this.MULTIPLE_PRICE;
    }
}

