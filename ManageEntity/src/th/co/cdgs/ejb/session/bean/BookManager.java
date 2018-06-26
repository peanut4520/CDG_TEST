package th.co.cdgs.ejb.session.bean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import th.co.cdgs.jpa.entity.Book;

@Stateless
@LocalBean
public class BookManager {

	@PersistenceContext(unitName = "trainlab")
	private EntityManager em;

	public void insertBook(Book book) {
		em.persist(book);
		book.setTitle("test Manage status");
	}

	public void updateBook(Book book) {
		Book manageBook = em.find(Book.class, book.getId());
		manageBook.setTitle(book.getTitle());
		manageBook.setAuthor(book.getAuthor());
		manageBook.setPublicationYear(book.getPublicationYear());
		manageBook.setUnitPrice(book.getUnitPrice());
	}
	

	public void mergeBook(Book book) {
		em.merge(book); 
	}
	public Book findBookById(Long id) {
		Book book =em.find(Book.class, id);
		return book;
	}
	public void deleteBook(Long id) {
		Book book =em.find(Book.class, id);
		em.remove(book);
	}

}
