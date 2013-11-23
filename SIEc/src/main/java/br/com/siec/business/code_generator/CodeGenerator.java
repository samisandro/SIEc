/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.business.code_generator;

/** 
 * Gera strings aleatorioas contendo letras e numeros.
 * @version 1.0.0 17 November, 2013
 * @author Josimar Alves
 */
public interface CodeGenerator {
    
    public String generateCode(int lenght, Mode mode);
    
}
