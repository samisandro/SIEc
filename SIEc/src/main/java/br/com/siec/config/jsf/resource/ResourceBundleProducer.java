package br.com.siec.config.jsf.resource;

import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

@RequestScoped
public class ResourceBundleProducer {

    private ResourceBundle messageResourceBundle;
    protected Logger logger = Logger.getLogger(ResourceBundleProducer.class);

    @Produces
    @MessageResourceBundle
    ResourceBundle getMessageResourceBundle() {
        if (null == messageResourceBundle) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            logger.debug("Getting message resource bundle for locale "+
                    facesContext.getViewRoot().getLocale());
            messageResourceBundle = facesContext.getApplication()
                    .getResourceBundle(facesContext, "bundle");
        }
        return messageResourceBundle;
    }
}
