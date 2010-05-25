package org.youthnet.debug.dao.admin;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: karl
 * Date: 25-May-2010
 */
@Repository("adminGenericDao")
@Transactional(readOnly = true)
public class AdminGenericDao {

    @Resource(name = "adminSessionFactory")
    private SessionFactory sessionFactory;

    public <D, I> D request(I id, Class<D> persistentClass) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq("id", id));
        criteria.setMaxResults(1);
        return (D)criteria.uniqueResult();
    }

    public <D> List<D> requestAll(Class<D> persistentClass) {
        return sessionFactory.getCurrentSession().createCriteria(persistentClass).list();
    }
}
