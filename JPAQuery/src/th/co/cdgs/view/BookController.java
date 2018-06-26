package th.co.cdgs.view;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import th.co.cdgs.ejb.session.bean.BookQuery;
import th.co.cdgs.jpa.entity.Book;


@ManagedBean
public class BookController {

	@EJB
	private BookQuery bookQuery;

	@ManagedProperty(value = "#{bookVM}")
	private BookVM bookVM;

	private List<Book> bookResults = new ArrayList<>();

	public void queryBookById() {
		Book book = bookQuery.queryBookById(bookVM.getId());
		bookResults.add(book);
	}

	public void queryBookByCondition() {
		Book book = new Book();
		book.setTitle(bookVM.getTitle());
		book.setPublicationYear(bookVM.getPublicationYear());
		List<Book> books = bookQuery.queryBookByCondition(book);
		setBookResults(books);
	}

	public void queryBookNativeById() {
		Book book = bookQuery.queryBookNativeById(bookVM.getId());
		bookResults.add(book);
	}

	public void queryBookNativeByCondition() {
		Book book = new Book();
		book.setTitle(bookVM.getTitle());
		book.setPublicationYear(bookVM.getPublicationYear());
		List<Book> books = bookQuery.queryBookNativeByCondition(book);
		setBookResults(books);
	}

	public BookVM getBookVM() {
		return bookVM;
	}

	public void setBookVM(BookVM bookVM) {
		this.bookVM = bookVM;
	}

	public List<Book> getBookResults() {
		return bookResults;
	}

	public void setBookResults(List<Book> bookResults) {
		this.bookResults = bookResults;
	}
}
