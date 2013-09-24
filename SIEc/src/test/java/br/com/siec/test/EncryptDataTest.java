package br.com.siec.test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import br.com.siec.api.resource.EncryptData;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Josimar
 */
public class EncryptDataTest {
    public static void main(String args []) throws NoSuchAlgorithmException, Exception{
        String password = "I'm The Batman!";
        String encrpytSHA = EncryptData.encryptSHA(password);
        String encrpytSHA256 = EncryptData.encryptSHA256(password);
        String encrpytAES128 = EncryptData.encryptAES128(password);
        String decrpytAES128 = EncryptData.decryptAES128(encrpytAES128);
        
        System.out.println("-------------Criptogrando senha--------------");
        System.out.println("Senha a ser criptografada: "+password);
        System.out.println("Senha criptografada SHA1: "+encrpytSHA);
        System.out.println("Senha criptografada SHA256: "+encrpytSHA256);
        System.out.println("Senha criptografada AES128: "+encrpytAES128);
        System.out.println("Senha descriptografada AES128: "+decrpytAES128);
        System.out.println("----------------------------------------------");
    }
    
}
