package org.obarcia.demo.services;

import org.obarcia.demo.components.datatables.DataTablesRequest;
import org.obarcia.demo.components.datatables.DataTablesResponse;
import org.obarcia.demo.models.user.User;
import org.obarcia.demo.models.user.UserLite;

/**
 * Servicio de usuarios.
 * 
 * @author obarcia
 */
public interface UserService
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
     * @return true si la operación fué correcta, false en caso contrario.
     */
    public boolean save(User user);
}