package org.obarcia.demo.dao;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.obarcia.demo.components.datatables.DataTablesOrder;
import org.obarcia.demo.components.datatables.DataTablesRequest;
import org.obarcia.demo.components.datatables.DataTablesResponse;
import org.obarcia.demo.models.user.User;
import org.obarcia.demo.models.user.UserLite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Implementación del UserDao.
 * 
 * @author obarcia
 */
@Repository
public class UserDaoImpl implements UserDao
{
    /**
     * Session factory.
     */
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public DataTablesResponse<UserLite> getUsersLite(DataTablesRequest req)
    {
        DataTablesResponse<UserLite> list = new DataTablesResponse<>();
        
        // Criteria
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        
        // Count
        CriteriaQuery<Long> criteriaCount = builder.createQuery(Long.class);
        criteriaCount.select(builder.count(criteriaCount.from(UserLite.class)));
        
        // Query
        CriteriaQuery<UserLite> criteria = builder.createQuery(UserLite.class);
        Root<UserLite> root = criteria.from(UserLite.class);
        
        List<Predicate> predicates = new LinkedList<>();
        
        // Filters by column
        // Id
        if (req.hasColumnSearch("id")) {
            predicates.add(builder.equal(root.get("id"), Integer.parseInt(req.getColumnSearch("id"))));
        }
        // Nickname
        if (req.hasColumnSearch("nickname")) {
            predicates.add(builder.like(builder.lower(root.<String>get("nickname")), "%" + req.getColumnSearch("nickname").toLowerCase() + "%"));
        }
        // Email
        if (req.hasColumnSearch("email")) {
            predicates.add(builder.like(builder.lower(root.<String>get("email")), "%" + req.getColumnSearch("email").toLowerCase() + "%"));
        }
        // User role
        if (req.hasColumnSearch("user_role")) {
            predicates.add(builder.equal(root.get("user_role"), req.getColumnSearch("user_role")));
        }
        // Created
        if (req.hasColumnSearch("created")) {
            predicates.add(builder.equal(root.get("created"), req.getColumnSearch("created")));
        }
        // Active
        if (req.hasColumnSearch("active")) {
            predicates.add(builder.equal(root.get("active"), Boolean.valueOf(req.getColumnSearch("active"))));
        }
        
        // Filters general
        if (!req.getSearch().isEmpty()) {
            predicates.add(builder.like(builder.lower(root.<String>get("nickname")), "%" + req.getSearch().toLowerCase() + "%"));
            predicates.add(builder.like(builder.lower(root.<String>get("email")), "%" + req.getSearch().toLowerCase() + "%"));
        }
        
        // Where
        if (predicates.size() > 0) {
            Predicate[] predArray = new Predicate[predicates.size()];
            predicates.toArray(predArray);
            criteriaCount.where(predArray);
            criteriaCount.where(builder.or(predArray));
            criteria.where(builder.or(predArray));
        }
        
        // Order
        List<Order> orders = new LinkedList<>();
        for (DataTablesOrder o: req.getOrders()) {
            if (o.getDir() == DataTablesOrder.ORDER_ASC) {
                orders.add(builder.asc(root.get(o.getData())));
            } else {
                orders.add(builder.desc(root.get(o.getData())));
            }
        }
        criteria.orderBy(orders);
        
        // Query
        Query<UserLite> q = sessionFactory.getCurrentSession().createQuery(criteria);
        q.setFirstResult(req.getStart()).setMaxResults(req.getLength());
        list.setDraw(req.getDraw());
        list.setRecordsTotal(sessionFactory.getCurrentSession().createQuery(criteriaCount).getSingleResult().intValue());
        list.setRecordsFiltered(list.getRecordsTotal());
        list.setData(q.list());
        
        return list;
    }
    @Override
    public User getUserById(int id)
    {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }
    @Override
    public User getUserByEmail(String email)
    {
        if (email != null && !email.isEmpty()) {
            CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> root = criteria.from(User.class);
            criteria.where(builder.equal(root.get("email"), email));
            Query<User> q = sessionFactory.getCurrentSession().createQuery(criteria);
            return q.uniqueResult();
        }
        
        return null;
    }
    @Override
    public User getUserByNickname(String nickname)
    {
        if (nickname != null && !nickname.isEmpty()) {
            CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> root = criteria.from(User.class);
            criteria.where(builder.equal(root.get("nickname"), nickname));
            Query<User> q = sessionFactory.getCurrentSession().createQuery(criteria);
            return q.uniqueResult();
        }
        
        return null;
    }
    @Override
    public User getUserByUkey(String ukey)
    {
        if (ukey != null && !ukey.isEmpty()) {
            CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> root = criteria.from(User.class);
            criteria.where(builder.equal(root.get("ukey"), ukey));
            Query<User> q = sessionFactory.getCurrentSession().createQuery(criteria);
            return q.uniqueResult();
        }
        
        return null;
    }
    @Override
    public void save(User user) throws HibernateException
    {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }
}