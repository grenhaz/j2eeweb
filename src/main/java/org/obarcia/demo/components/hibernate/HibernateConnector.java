package org.obarcia.demo.components.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author obarcia
 */
public class HibernateConnector
{
    private static final Logger LOGGER = Logger.getLogger(HibernateConnector.class.getName());
    
    private static HibernateConnector me;
    private final Configuration cfg;
    private final ServiceRegistry serviceRegistry;
    private SessionFactory sessionFactory;
 
    /**
     * Constructor de la clase.
     * @throws HibernateException 
     */
    private HibernateConnector() throws HibernateException
    {
        cfg = new Configuration().configure();
        serviceRegistry = new ServiceRegistryBuilder()
                .applySettings(cfg.getProperties())
                .buildServiceRegistry();
        sessionFactory = cfg.buildSessionFactory(serviceRegistry);
    }
    /**
     * Patrón SINGLETÓN.
     * @return Instancia del SINGLETÓN.
     * @throws HibernateException 
     */
    public static synchronized HibernateConnector getInstance() throws HibernateException
    {
        if (me == null) {
            me = new HibernateConnector();
        }
 
        return me;
    }
    /**
     * Obtener la sesión.
     * @return Instancia de la sesión.
     * @throws HibernateException 
     */
    public Session getSession() throws HibernateException
    {
        Session session = sessionFactory.openSession();
        if (!session.isConnected()) {
            this.reconnect();
        }
        return session;
    }
    /**
     * Reconectar.
     * @throws HibernateException 
     */
    private void reconnect() throws HibernateException
    {
        this.sessionFactory = cfg.buildSessionFactory(serviceRegistry);
    }
    /**
     * Devuelve una entidad por su Id.
     * @param cls Clase de la entidad.
     * @param id Identificador/es de búsqueda.
     * @return Entidad.
     */
    public Object get(Class cls, Serializable id)
    {
        Object model = null;
        Session session = null;
        
        try {
            session = getSession();
            model = session.get(cls, id);
        } catch(Exception sqlException) {
            LOGGER.log(Level.SEVERE, sqlException.toString(), sqlException);
        } finally {
            if(session != null) {
                ///session.close();
            }
        }

        return model;
    }
    /**
     * Obtener todos los registros.
     * @param cls Clase de las entidades.
     * @return Listado de entidades.
     */
    public List getAll(Class cls)
    {
        List models = null;
        Session session = null;
        
        try {
            session = getSession();
            models = session
                .createQuery("FROM " + cls.getCanonicalName())
                .list();
        } catch(Exception sqlException) {
            LOGGER.log(Level.SEVERE, sqlException.toString(), sqlException);
        } finally {
            if(session != null) {
                //session.close();
            }
        }

        return models;
    }
    /**
     * Guardar el registro en la BBDD.
     * @param entity Entidad a guardar.
     * @return true si la operación fué correcta, false en caso contrario.
     */
    public boolean save(Object entity)
    {
        boolean ret = false;
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();

            ret = true;
        } catch (Exception sqlException) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.log(Level.SEVERE, sqlException.toString(), sqlException);
        } finally {
            if (session != null) {
                //session.close();
            }
        }
        
        return ret;
    }
    /**
     * Eliminar le registro de la BBDD.
     * @param entity Entidad a eliminar.
     * @return true si la operación fué correcta, false en caso contrario.
     */
    public boolean delete(Object entity)
    {
        boolean ret = false;
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
            
            ret = true;
        } catch (Exception sqlException) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.log(Level.SEVERE, sqlException.toString(), sqlException);
        } finally {
            if (session != null) {
                //session.close();
            }
        }
        
        return ret;
    }
}