/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.resource.notification.qualifiers;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * @version 1.0.0 Octuber 15, 2013.
 * @author Josimar Alves
 */
@Qualifier
@Retention(RUNTIME)
@Target({METHOD, FIELD, PARAMETER, TYPE})
public @interface UserNotificationQualifier {
    
}
