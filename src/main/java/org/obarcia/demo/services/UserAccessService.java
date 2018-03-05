package org.obarcia.demo.services;

import java.util.HashSet;
import java.util.Set;
import org.obarcia.demo.dao.UserDao;
import org.obarcia.demo.models.user.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servicio de acceso de usuarios (login).
 * 
 * @author obarcia
 */
@Service("UserAccessService")
public class UserAccessService implements UserDetailsService
{
    /**
     * Instancia del DAO de usuarios.
     */
    @Autowired
    private UserDao userDao;
    
    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException
    {
        // Obtener el usuario por su email
        org.obarcia.demo.models.user.User user = userDao.getUserByEmail(string);
        if (user != null && user.getActive().equals(Boolean.TRUE)) {
            Set<GrantedAuthority> auths = new HashSet<>();
            auths.add(new SimpleGrantedAuthority(user.getUserRole()));

            return new AccountDetails(user.getEmail(), user.getPassword(),
                            true, true, true, true,
                            auths, user.getNickname(), user.getAvatar(), user.getId());
        } else {
            throw new UsernameNotFoundException("User '" + string + "' not found.");
        }
    }
}