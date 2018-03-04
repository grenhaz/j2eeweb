package org.obarcia.demo.services;

import org.obarcia.demo.components.datatables.DataTablesRequest;
import org.obarcia.demo.components.datatables.DataTablesResponse;
import org.obarcia.demo.dao.UserDao;
import org.obarcia.demo.models.user.User;
import org.obarcia.demo.models.user.UserLite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author obarcia
 */
@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserDao userDao;
    
    @Override
    public DataTablesResponse<UserLite> getUsersLite(DataTablesRequest req)
    {
        return userDao.getUsersLite(req);
    }
    @Override
    public User getUserById(int id)
    {
        return userDao.getUserById(id);
    }
    @Override
    public User getUserByEmail(String email)
    {
        return userDao.getUserByEmail(email);
    }
    @Override
    public User getUserByNickname(String nickname)
    {
        return userDao.getUserByNickname(nickname);
    }
    @Override
    public boolean save(User user)
    {
        return userDao.save(user);
    }
}
