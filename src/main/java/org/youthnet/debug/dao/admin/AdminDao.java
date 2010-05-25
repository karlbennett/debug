package org.youthnet.debug.dao.admin;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: karl
 * Date: 19-May-2010
 */
@Transactional(readOnly = true)
public abstract class AdminDao<D,I> {

    @Resource(name = "adminSessionFactory")
    private SessionFactory sessionFactory;

    private Class<D> persistentClass;

    public AdminDao(Class<D> persistentClass) {
        this.persistentClass = persistentClass;    
    }

    public D request(I id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(this.persistentClass);
        criteria.add(Restrictions.eq("id", id));
        criteria.setMaxResults(1);
        return (D)criteria.uniqueResult();
    }

    public List<D> requestAll() {
        return sessionFactory.getCurrentSession().createCriteria(this.persistentClass).list();
    }

    protected Class<D> getPersistentClass() {
        return persistentClass;
    }

    protected void setPersistentClass(Class<D> persistentClass) {
        this.persistentClass = persistentClass;
    }
}
