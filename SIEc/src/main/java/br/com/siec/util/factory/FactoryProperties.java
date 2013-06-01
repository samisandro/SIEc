package br.com.siec.util.factory;

import java.io.FileInputStream;
import java.util.Properties;

public class FactoryProperties {
    
    private static String TYPE_PROPERTIES = "classes.properties";

    private FactoryProperties() {
    }

    public static String getProperty(String typeClasse) {
        Properties propriedades = new Properties();
        try {
            FileInputStream classes = new FileInputStream(TYPE_PROPERTIES);
            propriedades.load(classes);
        } catch (Exception e) {
        }
        return propriedades.getProperty(typeClasse);
    }
}
