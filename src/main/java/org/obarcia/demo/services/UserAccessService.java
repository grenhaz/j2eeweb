package org.obarcia.demo.services;

import java.util.HashSet;
import java.util.Set;
import org.obarcia.demo.models.user.UserManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author obarcia
 */
@Service("UserAccessService")
public class UserAccessService implements UserDetailsService
{
    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException
    {
        org.obarcia.demo.models.user.User user = UserManager.getInstance().getUserByName(string);
        if (user != null && user.getActive() == Boolean.TRUE) {
            Set<GrantedAuthority> auths = new HashSet<>();
            auths.add(new SimpleGrantedAuthority(user.getUserRole()));

            return new User(user.getUsername(), user.getPassword(),
                            true, true, true, true,
                            auths);
        } else {
            throw new UsernameNotFoundException("User '" + string + "' not found.");
        }
    }
}