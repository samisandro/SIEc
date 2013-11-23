package br.com.siec.business.code_generator;

import static br.com.siec.business.code_generator.Mode.ALPHA;
import static br.com.siec.business.code_generator.Mode.ALPHANUMERIC;
import static br.com.siec.business.code_generator.Mode.NUMERIC;
import static br.com.siec.business.code_generator.Mode.UPPERCASE_ALPHANUMERIC;

/**
 * Gera strings aleatorias.
 * @version 1.0.0 17 November, 2013
 * @author Josimar Alves
 */
public class CodeGeneratorImpl implements CodeGenerator {

    @Override
    public String generateCode(int length, Mode mode) {
         StringBuilder buffer = new StringBuilder();
        String characters = "";

        switch (mode) {

            case ALPHA:
                characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                break;

            case ALPHANUMERIC:
                characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                break;
                
            case UPPERCASE_ALPHANUMERIC:
                characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                break;

            case NUMERIC:
                characters = "1234567890";
                break;
        }

        int charactersLength = characters.length();

        for (int i = 0; i < length; i++) {
            double index = Math.random() * charactersLength;
            buffer.append(characters.charAt((int) index));
        }
        return buffer.toString();
    }
}
