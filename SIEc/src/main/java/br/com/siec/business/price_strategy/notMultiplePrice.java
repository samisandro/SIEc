/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.business.price_strategy;

import br.com.siec.model.persistence.entity.Preco;
import br.com.siec.model.persistence.resource.TipoPreco;
import java.util.List;
import java.util.Map;

/**
 *
 * @author josimar
 */
public class notMultiplePrice implements MultiplePrice {
    
    private final boolean MULTIPLE_PRICE = false;

    @Override
    public List<Preco> addPrice(List<Preco> prices, Preco preco){
        if (prices.size() >= 1) {
            prices.clear();
        }
        prices.add(preco);
        return prices;
    }

    @Override
    public boolean isMultiplePrice() {
        return this.MULTIPLE_PRICE;
    }
}
