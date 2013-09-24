/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.controller.resource;

import br.com.siec.model.persistence.entity.Produto;
import br.com.siec.service.ProdutoService;
import br.com.siec.service.qualifiers.ProdutoServiceQualifier;
import java.io.IOException;
import java.io.OutputStream;


import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

@WebServlet("/images/*")
public class ImageRendererServlet extends HttpServlet {

    @Inject
    @ProdutoServiceQualifier
    private ProdutoService produtoService;
    
    private static final Logger LOGGER = Logger.getLogger(ImageRendererServlet.class.getName());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        long id = Long.parseLong(request.getParameter("id"));

        if (id != 0) {

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Buscando Imagem.");
                LOGGER.debug("Id do Produto [" + id + "]");
            }
            Produto p = produtoService.find(id);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Produto Buscado: Nome [" + p.getNome() + "]");
            }
            try {
                response.setContentType(p.getImagem().getExtensao());
                OutputStream out = response.getOutputStream();
                out.write(p.getImagem().getArquivo());
                out.close();
            } catch (Exception e) {
                System.out.println("Exception Renderizando Imagem: "+e);
            }
        }

    }
}
