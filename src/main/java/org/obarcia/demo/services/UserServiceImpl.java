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
    public User getUserByEmail(String email)
    {
        return userDao.getUserByEmail(email);
    }
    @Override
    public boolean save(User user)
    {
        return userDao.save(user);
    }
}
