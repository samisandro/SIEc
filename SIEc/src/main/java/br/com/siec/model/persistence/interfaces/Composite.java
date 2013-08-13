/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.model.persistence.interfaces;

import br.com.siec.model.persistence.interfaces.IComponente;
import java.util.List;

/**
 *
 * @author josimar
 */
public interface Composite extends IProduto {

    public List<IComponente> getProdutos();

    public void addComponente(IComponente componente);

    public void removeComponente(IComponente componente);

    public IComponente getComponente(int i);
}
