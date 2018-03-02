package org.obarcia.demo.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.obarcia.demo.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author obarcia
 */
@Repository
public class UserDaoImpl implements UserDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public User getUserByEmail(String email)
    {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        criteria.where(builder.equal(userRoot.get("email"), email));
        Query<User> q = sessionFactory.getCurrentSession().createQuery(criteria);
        return q.uniqueResult();
    }
    @Override
    @Transactional
    public boolean save(User user)
    {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(user);
            
            return true;
        } catch (Exception e) {
        }
        
        return false;
    }
}