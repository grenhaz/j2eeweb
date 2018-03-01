package org.obarcia.demo.services;

import java.util.HashSet;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.obarcia.demo.components.hibernate.HibernateConnector;
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
        try {
            Session session = HibernateConnector.getInstance().getSession();
            Criteria criteria = session.createCriteria(org.obarcia.demo.models.user.User.class);

            criteria.add(Restrictions.eq("username", string));
            org.obarcia.demo.models.user.User user = (org.obarcia.demo.models.user.User)criteria.uniqueResult();
            if (user != null) {
                Set<GrantedAuthority> auths = new HashSet<>();
                auths.add(new SimpleGrantedAuthority(user.getUserRole()));

                return new User(user.getUsername(), user.getPassword(),
                                true, true, true, true,
                                auths);
            }
        } catch(Exception sqlException) {
            //sqlException.printStackTrace();
        }
        
        return null;
    }
}