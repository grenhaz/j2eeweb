package org.obarcia.demo.components;

import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.springframework.beans.BeanUtils.getPropertyDescriptor;

/**
 * Clase con utilidades varias.
 * 
 * @author obarcia
 */
public class Utilities
{
    private final static Logger LOGGER = Logger.getLogger(Utilities.class.getCanonicalName());
    
    /**
     * Devuelve un texto aleatoria formado por valores hexadecimales.
     * @param numchars Número de caracteres.
     * @return Texto aleatorio.
     */
    public final static String getRandomHexString(int numchars)
    {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < numchars) {
            sb.append(Integer.toHexString(r.nextInt()));
        }

        return sb.toString().substring(0, numchars);
    }
    /**
     * Devuelve el valor de una propiedad de una instancia BEAN.
     * @param bean Instancia.
     * @param property Nombre de la propiedad.
     * @return Valor de la propiedad o null si no la encuentra.
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public static Object getPropertyValue(Object bean, String property) 
        throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        Class<?> beanClass = bean.getClass();
        PropertyDescriptor propertyDescriptor = getPropertyDescriptor(beanClass, property);
        if (propertyDescriptor == null) {
            return null;
        }

        Method readMethod = propertyDescriptor.getReadMethod();
        if (readMethod == null) {
            return null;
        }
        return readMethod.invoke(bean);
    }
}