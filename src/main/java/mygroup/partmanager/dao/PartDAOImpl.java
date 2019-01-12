package mygroup.partmanager.dao;

import mygroup.partmanager.model.Part;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PartDAOImpl implements PartDAO{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int partsCount(Boolean required, String search) {
        Session session = sessionFactory.getCurrentSession();
        Query query;
        query = session.createQuery("select count(*) from Part where 1 = 1 and " +
                "(:required is null or required = :required) and " +
                "(:search is null or lower(title) like lower(:search))");
        query.setParameter("required", required);
        query.setParameter("search", "%" + search + "%");
        return ((Number) query.getSingleResult()).intValue();
    }

    @Override
    public int allPartsCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(*) from Part", Number.class).getSingleResult().intValue();
    }

    @Override
    public int computersCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select min(quantity) from Part where required = true", Number.class).getSingleResult().intValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Part> allParts(int page, Boolean required, String search) {
        Session session = sessionFactory.getCurrentSession();
        Query query;
        query = session.createQuery("from Part where 1 = 1 and " +
                "(:required is null or required = :required) and " +
                "(:search is null or lower(title) like lower(:search))");
        query.setParameter("required", required);
        query.setParameter("search", "%" + search + "%");
        return query.setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }

    @Override
    public void add(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(part);
    }

    @Override
    public void delete(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(part);
    }

    @Override
    public void edit(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.update(part);
    }

    @Override
    public Part getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Part.class, id);
    }

    public boolean checkTitle(String title) {
        Session session = sessionFactory.getCurrentSession();
        Query query;
        query = session.createQuery("from Part where title = :title");
        query.setParameter("title", title);
        return query.list().isEmpty();
    }
}
