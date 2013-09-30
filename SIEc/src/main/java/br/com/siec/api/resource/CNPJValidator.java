/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.api.resource;

import java.util.InputMismatchException;

/**
 *
 * @author josimar
 */
public class CNPJValidator {

    public static boolean isValidCNPJ(String cnpj) {

        cnpj = retirarMascara(cnpj);

        if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111")
                || cnpj.equals("22222222222222") || cnpj.equals("33333333333333")
                || cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
                || cnpj.equals("66666666666666") || cnpj.equals("77777777777777")
                || cnpj.equals("88888888888888") || cnpj.equals("99999999999999")
                || (cnpj.length() != 14)) {
            return (false);
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig13 = '0';
            } else {
                dig13 = (char) ((11 - r) + 48);
            }

            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig14 = '0';
            } else {
                dig14 = (char) ((11 - r) + 48);
            }

            if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException e) {
            return (false);
        }
    }

    public static String retirarMascara(String cnpj) {
        String cnpjNoMascara;
        
        if (cnpj.length() == 18) {
            
            cnpjNoMascara = cnpj.substring(0, 2);
            cnpjNoMascara += cnpj.substring(3, 6);
            cnpjNoMascara += cnpj.substring(7, 10);
            cnpjNoMascara += cnpj.substring(11, 15);
            cnpjNoMascara += cnpj.substring(16, 18);          
             System.out.println("CNPJ: "+cnpjNoMascara);
            return cnpjNoMascara;
        } else {
             System.out.println("CNPJ: "+cnpj);
            return cnpj;
        }

    }
}
