package br.com.siec.business.validator;

import java.util.Calendar;
import java.util.Date;

/**
 * @version 16 November, 2013.
 * @author Josimar Alves
 */
public class AgeValidator {

    public static boolean isValidAge(Date birthday) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(birthday);
        c2.setTime(new Date());

        return (c2.getTimeInMillis() - c1.getTimeInMillis()) < 18;
    }
}
