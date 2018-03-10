package org.obarcia.demo.dao;

import org.hibernate.HibernateException;
import org.obarcia.demo.components.datatables.DataTablesRequest;
import org.obarcia.demo.components.datatables.DataTablesResponse;
import org.obarcia.demo.models.user.User;
import org.obarcia.demo.models.user.UserLite;

/**
 * DAO de Usuarios.
 * 
 * @author obarcia
 */
public interface UserDao
{
    /**
     * Devuelve un listado de usuarios utilizando el DataTables.
     * @param req Instancia de la petición.
     * @return Listado de usuarios.
     */
    public DataTablesResponse<UserLite> getUsersLite(DataTablesRequest req);
    /**
     * Busca un usuario por su identificador.
     * @param id Identificador.
     * @return Instancia del usuario o null si no lo encuentra.
     */
    public User getUserById(int id);
    /**
     * Busca un usuario por su email.
     * @param email E-mail.
     * @return Instancia del usuario o null si no lo encuentra.
     */
    public User getUserByEmail(String email);
    /**
     * Busca un usuario por su nickname.
     * @param nickname Nickname.
     * @return Instancia del usuario o null si no lo encuentra.
     */
    public User getUserByNickname(String nickname);
    /**
     * Busca un usuario por su clave de usuario.
     * @param ukey clave del usuario.
     * @return Instancia del usuario o null si no lo encuentra.
     */
    public User getUserByUkey(String ukey);
    /**
     * Guarda un usuario.
     * @param user Instancia del usuario.
     * @throws HibernateException
     */
    public void save(User user) throws HibernateException;
}