package br.com.siec.api.factory.util.cepwebservice;

import br.com.siec.model.persistence.entity.Endereco;
import br.com.siec.model.persistence.resource.Estados;
import java.net.URL;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @version 1.0.0 September 28, 2013
 * @author Josimar Alves
 */
public class CepWebServiceImpl implements CepWebService {

    private Endereco endereco;
    
    private int resultado = 0;

    @Override
    @SuppressWarnings("rawtypes")
    public Endereco loadByCep(String cep) {

        try {
            URL url = new URL(
                    "http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep
                    + "&formato=xml");

            Document document = getDocumento(url);

            Element root = document.getRootElement();

            for (Iterator i = root.elementIterator(); i.hasNext();) {
                Element element = (Element) i.next();

                if (element.getQualifiedName().equals("uf")) {
                    endereco.setEstado(Estados.valueOf(element.getText()));
                }

                if (element.getQualifiedName().equals("cidade")) {
                    endereco.setCidade(element.getText());
                }

                if (element.getQualifiedName().equals("bairro")) {
                    endereco.setBairro(element.getText());
                }

                if (element.getQualifiedName().equals("logradouro")) {
                    endereco.setLogradouro(element.getText());
                }

                if (element.getQualifiedName().equals("resultado")) {
                    setResultado(Integer.parseInt(element.getText()));
                }
            }
            
            return endereco;
        } catch (Exception ex) {
        }
        return endereco;
    }

    public Document getDocumento(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);

        return document;
    }
    
    private void setResultado(int resultado){
        this.resultado = resultado;
    }
}
