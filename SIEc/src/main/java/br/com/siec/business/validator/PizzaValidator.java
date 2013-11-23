/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.business.validator;

import br.com.siec.model.persistence.interfaces.Composite;
import br.com.siec.model.persistence.interfaces.IComponente;
import br.com.siec.model.persistence.resource.Categorias;

/**
 *
 * @author josimar
 */
public class PizzaValidator {

    public static boolean isValid(Composite pizza) {

        int borda = 0;
        int massa = 0;
        int recheio = 0;

        for (IComponente componente : pizza.getComponentes()) {

            if (componente.getCategoria().equals(Categorias.Borda)) {
                borda++;
            }
            if (componente.getCategoria().equals(Categorias.Massa)) {
                massa++;
            }
            if (componente.getCategoria().equals(Categorias.Ingredientes)) {
                recheio++;
            }

        }

        if (massa < 1) {
            return false;
        }
        if (borda < 1) {
            return false;
        }
        if (recheio < 3) {
            return false;
        }

        return true;
    }
}
