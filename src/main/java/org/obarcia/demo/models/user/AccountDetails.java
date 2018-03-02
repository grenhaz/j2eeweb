package org.obarcia.demo.models.user;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Heck
 */
public class AccountDetails extends User
{
    private String avatar;
    
    public AccountDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String avatar)
    {
        super(username, password, authorities);
        
        this.avatar = avatar;
    }
    public AccountDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String avatar)
    {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        
        this.avatar = avatar;
    }
    public String getAvatar()
    {
        return (avatar != null && !avatar.isEmpty() ? avatar : "anonymous.png");
    }
    public void setAvatar(String value)
    {
        avatar = value;
    }
}
