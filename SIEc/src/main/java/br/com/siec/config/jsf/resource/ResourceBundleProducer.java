package br.com.siec.config.jsf.resource;

import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import org.jboss.logging.Logger;

@RequestScoped
public class ResourceBundleProducer {

    private ResourceBundle messageResourceBundle;
    protected Logger logger = Logger.getLogger(ResourceBundleProducer.class);

    @Produces
    @MessageResourceBundle
    ResourceBundle getMessageResourceBundle() {
        if (null == messageResourceBundle) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            logger.debugv("Getting message resource bundle for locale {0}.",
                    facesContext.getViewRoot().getLocale());
            messageResourceBundle = facesContext.getApplication()
                    .getResourceBundle(facesContext, "bundle");
        }
        return messageResourceBundle;
    }
}
