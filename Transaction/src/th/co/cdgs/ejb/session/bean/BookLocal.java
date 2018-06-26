package th.co.cdgs.ejb.session.bean;

import java.util.List;

import javax.ejb.Local;

import th.co.cdgs.jpa.entity.Book;



@Local
public interface BookLocal {
	public Book insertBook(Book book);
	public Book insertBookRequiresNew(Book book);
	public Book insertBookSupport(Book book);
	public List<Book> queryBookSupport();
	public List<Book> queryBookNotSupport();
	
	public List<Book> queryBookNever();
	
	
	public void updateBook(Book book);
	public void mergeBook(Book book);
	public Book findBookById(Long id);
	public void deleteBook(Long id);
}
