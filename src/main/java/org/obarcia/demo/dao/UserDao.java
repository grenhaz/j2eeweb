package org.obarcia.demo.dao;

import org.obarcia.demo.models.user.User;

/**
 *
 * @author obarcia
 */
public interface UserDao
{
    public User getUserByName(String username);
    public boolean save(User user);
}