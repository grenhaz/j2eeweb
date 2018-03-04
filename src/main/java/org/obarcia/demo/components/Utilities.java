package org.obarcia.demo.components;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;
import javax.management.IntrospectionException;
import static org.springframework.beans.BeanUtils.getPropertyDescriptor;

/**
 *
 * @author Heck
 */
public class Utilities
{
    public final static String getRandomHexString(int numchars)
    {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < numchars) {
            sb.append(Integer.toHexString(r.nextInt()));
        }

        return sb.toString().substring(0, numchars);
    }
    public static Object getPropertyValue(Object bean, String property)
        throws IntrospectionException, IllegalArgumentException,
        IllegalAccessException, InvocationTargetException
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
