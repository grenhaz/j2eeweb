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
    private Integer id;
    private String nickname;
    private String avatar;
    
    public AccountDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String nickname, String avatar, int id)
    {
        super(username, password, authorities);
        
        this.id = id;
        this.nickname = nickname;
        this.avatar = avatar;
    }
    public AccountDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String nickname, String avatar, int id)
    {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        
        this.id = id;
        this.nickname = nickname;
        this.avatar = avatar;
    }
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer value)
    {
        id = value;
    }
    public String getNickname()
    {
        return nickname;
    }
    public void setNickname(String value)
    {
        nickname = value;
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
