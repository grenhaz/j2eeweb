package org.obarcia.demo.dao;

import org.obarcia.demo.models.user.User;

/**
 *
 * @author obarcia
 */
public interface UserDao
{
    public User getUserByEmail(String email);
    public boolean save(User user);
}