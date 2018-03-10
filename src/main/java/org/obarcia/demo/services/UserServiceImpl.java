package org.obarcia.demo.services;

import org.hibernate.HibernateException;
import org.obarcia.demo.components.datatables.DataTablesRequest;
import org.obarcia.demo.components.datatables.DataTablesResponse;
import org.obarcia.demo.dao.UserDao;
import org.obarcia.demo.exceptions.SaveException;
import org.obarcia.demo.models.user.User;
import org.obarcia.demo.models.user.UserLite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio de usuarios.
 * 
 * @author obarcia
 */
@Service
public class UserServiceImpl implements UserService
{
    /**
     * Instacnia del DAO de usuarios.
     */
    @Autowired
    private UserDao userDao;
    
    @Override
    @Transactional(readOnly = true)
    public DataTablesResponse<UserLite> getUsersLite(DataTablesRequest req)
    {
        return userDao.getUsersLite(req);
    }
    @Override
    @Transactional(readOnly = true)
    public User getUserById(int id)
    {
        return userDao.getUserById(id);
    }
    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email)
    {
        return userDao.getUserByEmail(email);
    }
    @Override
    @Transactional(readOnly = true)
    public User getUserByNickname(String nickname)
    {
        return userDao.getUserByNickname(nickname);
    }
    @Override
    @Transactional(readOnly = true)
    public User getUserByUkey(String ukey)
    {
        return userDao.getUserByUkey(ukey);
    }
    @Override
    @Transactional
    public void save(User user) throws SaveException
    {
        try {
            userDao.save(user);
        } catch (HibernateException e) {
            throw new SaveException();
        }
    }
}