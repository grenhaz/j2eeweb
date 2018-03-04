package org.obarcia.demo.dao;

import org.obarcia.demo.components.datatables.DataTablesRequest;
import org.obarcia.demo.components.datatables.DataTablesResponse;
import org.obarcia.demo.models.user.User;
import org.obarcia.demo.models.user.UserLite;

/**
 *
 * @author obarcia
 */
public interface UserDao
{
    public DataTablesResponse<UserLite> getUsersLite(DataTablesRequest req);
    public User getUserById(int id);
    public User getUserByEmail(String email);
    public User getUserByNickname(String nickname);
    public boolean save(User user);
}