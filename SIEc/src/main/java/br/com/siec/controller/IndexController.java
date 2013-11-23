/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author Josimar Alves
 */
@ManagedBean(name = "indexController")
@SessionScoped
public class IndexController implements Serializable{
    
    private MapModel simpleModel;    
    
    @PostConstruct
    public void init() {  
        simpleModel = new DefaultMapModel();  
        
        //Shared coordinates  
        LatLng coord1 = new LatLng(-16.361115, -46.900273);  
              
        //Basic marker  
        simpleModel.addOverlay(new Marker(coord1, "Smart Pizzaria"));      
    }  
  
    public MapModel getSimpleModel() {  
        return simpleModel;  
    }
}
