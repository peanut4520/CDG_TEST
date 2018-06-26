package th.co.cdgs.ejb.session.bean;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import th.co.cdgs.jpa.entity.Book;
import th.co.cdgs.jpa.entity.City;
import th.co.cdgs.jpa.entity.Country;

@Stateless
@LocalBean
public class BookQuery {

	@PersistenceContext
	private EntityManager entityManager;

	public Book queryBookById(long id) {
		final String jpql = "select c from Book c where c.id = :id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		Book result = (Book) query.getSingleResult();
		return result;
	}

	public List<Book> queryBookByCondition(Book book) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("select b from Book b where 1=1");
		if (book.getTitle() != null && !"".equals(book.getTitle())) {
			jpql.append(" and b.title = :title");
		}
		if (book.getAuthor() != null) {
			jpql.append(" and b.auther = :auther");
		}
		if (book.getPublicationYear() != null) {
			jpql.append(" and b.publicationYear = :publicationYear");
		}
		Query query = entityManager.createQuery(jpql.toString());
		if (book.getTitle() != null && !"".equals(book.getTitle())) {
			query.setParameter("title", book.getTitle());
		}
		if (book.getAuthor() != null && !"".equals(book.getAuthor())) {
			query.setParameter("auther", book.getAuthor());
		}
		if (book.getPublicationYear() != null && !"".equals(book.getPublicationYear())) {
			query.setParameter("publicationYear", book.getPublicationYear());
		}
		return (List<Book>) query.getResultList();
	}

	public Book queryBookNativeById(long id) {
		final String sql = "select id, title, author, publication_year , unit_price from book where id = :id ";
		// Query query = entityManager.createNativeQuery(sql, City.class);
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("id", id);
		// City city = (City) query.getSingleResult();
		Object[] objects = (Object[]) query.getSingleResult();
		Book book = new Book();
		book.setId(((Integer) objects[0]).longValue());
		book.setTitle((String) objects[1]);
		book.setAuthor((String) objects[2]);
		book.setPublicationYear(((Integer) objects[3]).longValue());
		book.setUnitPrice((BigDecimal) objects[4]);
		return book;

	}

	public List<Book> queryBookNativeByCondition(Book book) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from Book b where 1=1");
		if (book.getTitle() != null && !"".equals(book.getTitle())) {
			sql.append("and b.title = :title");
		}
		if (book.getAuthor() != null) {
			sql.append("and b.author = :author");
		}
		if (book.getPublicationYear() != null) {
			sql.append("and b.publication_year = :publicationYear");
		}
		Query query = entityManager.createNativeQuery(sql.toString(),Book.class);
		if (book.getTitle() != null && !"".equals(book.getTitle())) {
			query.setParameter("title", book.getTitle());
		}
		if (book.getAuthor() != null && !"".equals(book.getAuthor())) {
			query.setParameter("author", book.getAuthor());
		}
		if (book.getPublicationYear() != null) {
			query.setParameter("publicationYear", book.getPublicationYear());
		}
		List<Book> results = query.getResultList();
		return results;
	}
}
