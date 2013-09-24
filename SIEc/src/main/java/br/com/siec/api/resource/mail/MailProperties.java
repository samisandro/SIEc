/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.api.resource.mail;

import java.io.FileInputStream;
import java.util.Properties;

/**
 *
 * @author Josimar
 */
public class MailProperties {

    private MailProperties() {
    }

    public static Properties getInstance(String properties) {
        Properties propriedades = new Properties();
        try {
            FileInputStream classes = new FileInputStream(properties);
            propriedades.load(classes);
        } catch (Exception e) {
        }
        return propriedades;
    }
}
