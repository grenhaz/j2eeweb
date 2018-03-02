package org.obarcia.demo.services;

import org.obarcia.demo.dao.UserDao;
import org.obarcia.demo.models.user.User;
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
    public User getUserByName(String username)
    {
        return userDao.getUserByName(username);
    }
    @Override
    public boolean save(User user)
    {
        return userDao.save(user);
    }
}
