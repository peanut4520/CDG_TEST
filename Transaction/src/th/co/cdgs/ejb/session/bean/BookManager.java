package th.co.cdgs.ejb.session.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import th.co.cdgs.jpa.entity.Book;

@Stateless
public class BookManager implements BookLocal {

	@PersistenceContext(unitName = "trainlab")
	private EntityManager entityManager;

	@Override
	public Book insertBook(Book book) {
		entityManager.persist(book);
		return book;
	}

	@Override
	public void updateBook(Book book) {
		 Book managedBook = entityManager.find(Book.class, book.getId());
		 managedBook.setTitle(book.getTitle());
		 managedBook.setAuthor(book.getAuthor());
		 managedBook.setPublicationYear(book.getPublicationYear());
		 managedBook.setUnitPrice(book.getUnitPrice());
		// em.merge(managedBook);  ทดสอบแบบไม่ใช้ merge ให้ดูก่อน
	}

	@Override
	public void mergeBook(Book book) {
		entityManager.merge(book);
	}

	@Override
	public Book findBookById(Long id) {
		Book managedBook = entityManager.find(Book.class, id);
		return managedBook;
	}

	@Override
	public void deleteBook(Long id) {
		entityManager.remove(entityManager.find(Book.class, id));
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Book insertBookRequiresNew(Book book) {
		entityManager.persist(book);
		return book;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Book insertBookSupport(Book book) {
		entityManager.persist(book);
		return book;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Book> queryBookNotSupport() {
		String jpql = "select b from Book b";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Book> queryBookSupport() {
		String jpql = "select b from Book b";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public List<Book> queryBookNever() {
		String jpql = "select b from Book b";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

	
	
	

}
