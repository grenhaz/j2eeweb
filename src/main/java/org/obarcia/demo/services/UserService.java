package org.obarcia.demo.services;

import org.obarcia.demo.models.user.User;

/**
 *
 * @author obarcia
 */
public interface UserService
{
    public User getUserByEmail(String email);
    public boolean save(User user);
}