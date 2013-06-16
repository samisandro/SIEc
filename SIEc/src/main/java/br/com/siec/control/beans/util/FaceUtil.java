/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.control.beans.util;

import java.io.Serializable;
import javax.faces.context.FacesContext;

/**
 *
 * @author josimar
 */
public class FaceUtil implements Serializable {

    /**
     * Creates a new instance of UtilBean
     */
    public FaceUtil() {
    }

    public static <T> T findBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return (T) context.getELContext().getELResolver().getValue(context.getELContext(), beanName, context);
    }
}
