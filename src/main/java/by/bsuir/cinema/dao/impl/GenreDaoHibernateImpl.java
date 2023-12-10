package by.bsuir.cinema.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import by.bsuir.cinema.dao.GenreDao;
import by.bsuir.cinema.domain.Genre;

@Component
public class GenreDaoHibernateImpl implements GenreDao {

	@Override
	public void create(Genre entity) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(entity);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public Genre read(int id) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Genre.class);
		criteria.add(Restrictions.eq("id", id));
		Genre genre = (Genre) criteria.uniqueResult();
		session.close();
		return genre;
	}

	@Override
	public void update(Genre entity) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		session.update(entity);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void delete(Genre entity) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		session.getTransaction().begin();
		session.delete(entity);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<Genre> readAll() {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Genre.class);
		// delete duplicates in "left outer join" query
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Genre> genres = criteria.list();
		session.close();
		return genres;
	}

	@Override
	public List<Genre> readAll(String sortingColumn) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Genre.class);
		// delete duplicates in "left outer join" query
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.asc(sortingColumn));
		List<Genre> genres = criteria.list();
		session.close();
		return genres;
	}
}
