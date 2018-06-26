package th.co.cdgs.view;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import th.co.cdgs.ejb.session.bean.BookManager;
import th.co.cdgs.jpa.entity.Book;

@ManagedBean
public class BookContoller {

	@EJB
	private BookManager bookManager;

	@ManagedProperty(value = "#{bookVM}")

	private BookVM bookVM;

	public void insertBook() {
		Book book = new Book();
		book.setId(bookVM.getId());
		book.setTitle(bookVM.getTitle());
		book.setAuthor(bookVM.getAuthor());
		book.setPublicationYear(bookVM.getPublicationYear());
		book.setUnitPrice(bookVM.getUnitPrice());

		bookManager.insertBook(book);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Insert Complete"));
	}

	public void updateBook() {
		Book book = new Book();
		book.setId(bookVM.getId());
		book.setTitle(bookVM.getTitle());
		book.setAuthor(bookVM.getAuthor());
		book.setPublicationYear(bookVM.getPublicationYear());
		book.setUnitPrice(bookVM.getUnitPrice());

		bookManager.updateBook(book);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("UPDATE Complete"));
	}

	public void updateBookWithMerge() {
		Book detechBook = bookManager.findBookById(bookVM.getId());
		if (detechBook != null) {
			detechBook.setTitle(bookVM.getTitle());
			detechBook.setAuthor(bookVM.getAuthor());
			detechBook.setPublicationYear(bookVM.getPublicationYear());
			detechBook.setUnitPrice(bookVM.getUnitPrice()); 
			
			/// SEND TO MANAGE
			
			bookManager.mergeBook(detechBook);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("UPDATE Merge Complete"));
		}
		
		
	}

	public void findBookById() {
		Book book = bookManager.findBookById(bookVM.getId()); // Book book =... // RETURN Object Book
		if (book != null) {
			bookVM.setTitle(book.getTitle());
			bookVM.setAuthor(book.getAuthor());
			bookVM.setPublicationYear(book.getPublicationYear());
			bookVM.setUnitPrice(book.getUnitPrice());
		} else {
			bookVM.setTitle(null);
			bookVM.setAuthor(null);
			bookVM.setPublicationYear(null);
			bookVM.setUnitPrice(null);
		}
	}

	public void deleteBook() {
		bookManager.deleteBook(bookVM.getId()); // NOT RETURN
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Delete Now"));
	}

	public BookVM getBookVM() {
		return bookVM;
	}

	public void setBookVM(BookVM bookVM) {
		this.bookVM = bookVM;
	}

}
