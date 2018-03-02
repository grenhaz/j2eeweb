package org.obarcia.demo.services;

import org.obarcia.demo.models.user.User;

/**
 *
 * @author obarcia
 */
public interface UserService
{
    public User getUserByName(String username);
    public boolean save(User user);
}