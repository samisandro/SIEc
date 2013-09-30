package br.com.siec.controller;

import br.com.siec.config.jsf.ViewContext;
import br.com.siec.model.persistence.entity.Componente;
import br.com.siec.model.persistence.entity.Composicao;
import br.com.siec.model.persistence.resource.Categorias;
import br.com.siec.service.ProdutoService;
import br.com.siec.service.qualifiers.ProdutoServiceQualifier;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "composicaoController")
@ViewScoped
public class ComposicaoController {

    @Inject
    @ProdutoServiceQualifier
    private ProdutoService produtoService;
    
    @Inject
    private ViewContext viewContext;
    
    private Composicao pizza;
    private List<Composicao> pizzas;
    private List<Composicao> filteredPizzas;
    
    private Componente aux;
    
    private List<Componente> componentes;
    private List<Componente> ingredientes;
    private List<Componente> bordas;
    private List<Componente> massas;
    
    private static final String HOME = "home";
    private static final String PIZZAS = "allPizza";
    private static final String UPDATE = "updatePizza"/*?faces-redirect=true&includeViewParams=true"*/;

    @PostConstruct
    public void init() {
        
        pizza = viewContext.getObjectInSession("pizza");
        viewContext.removeObjectInSession("pizza");
        
        if (pizza == null) {
            pizza = (Composicao) produtoService.create("Composite");
            pizza.setCategoria(Categorias.Composicao);
        }
    }

    public String save() {
        if (this.produtoService.save(pizza)) {
            viewContext.info("msg_info_saved");
            return PIZZAS;
        } else {
            viewContext.error("msg_error");
            return HOME;
        }
    }

    public String goUpdate() {
        viewContext.setObjectInSession("pizza", pizza);
        return UPDATE;
    }

    public String update() {
        if (this.produtoService.update(pizza)) {
            viewContext.info("msg_info_updated");
            return PIZZAS;
        } else {
            viewContext.error("msg_error");
            return HOME;
        }
    }
    
    public String delete() {
        if (this.produtoService.delete(pizza)) {
            this.pizzas.remove(pizza);
            viewContext.info("msg_info_deleted");
            return PIZZAS;
        } else {
            viewContext.error("msg_error");
            return HOME;
        }
    }

    public Composicao getPizza() {
        return this.pizza;
    }
    
    public void setPizza(Composicao pizza) {
        this.pizza = pizza;
    }

    public List<Composicao> getPizzas() {
        if (pizzas == null) {
            pizzas = produtoService.listComposite();
        }
        return pizzas;
    }

    public List<Componente> getIngredientes() {
        if (ingredientes == null) {
            ingredientes = produtoService.filterComponenteByCategory(Categorias.Ingredientes);
        }
        return ingredientes;
    }

    public List<Componente> getBordas() {
        if (bordas == null) {
            bordas = produtoService.filterComponenteByCategory(Categorias.Borda);
        }
        return bordas;
    }

    public List<Componente> getMassas() {
        if (massas == null) {
            massas = produtoService.filterComponenteByCategory(Categorias.Massa);
        }
        return massas;
    }

    public List<Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
    }

    public Componente getAux() {
        return this.aux;
    }

    public void setAux(Componente aux) {
        this.aux = aux;
    }

    public void addIngrediente(ActionEvent actionEvent) {
        this.pizza.removeComponente(getAux());
        this.pizza.addComponente(aux);
    }

    public void removeIngrediente(ActionEvent actionEvent) {
        this.pizza.removeComponente(aux);
    }

    public void onRowSelected(SelectEvent event) {
        this.pizza.removeByCategory(getAux().getCategoria());
        this.pizza.addComponente(aux);
    }
    
    public List<Composicao> getFilteredPizzas(){
        return this.filteredPizzas;
    }
    
    public void setFilteredPizzas(List<Composicao> pizzas){
        this.filteredPizzas = pizzas;
    }
    
}
