package th.co.cdgs.view;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import th.co.cdgs.ejb.session.bean.BookLocal;
import th.co.cdgs.ejb.session.bean.BookTranLocal;
import th.co.cdgs.jpa.entity.Book;

@ManagedBean
public class BookController {

	@EJB
	private BookTranLocal bookTranLocal;

	@EJB
	private BookLocal bookLocal;

	public void testRequire() {
		bookTranLocal.testRequire();
	}

	public void testRequiresNew() {
		bookTranLocal.testRequireNew();
	}

	public void testSupport() {
		bookTranLocal.testSupport();
	}

	public void testSupport2() {
		List<Book> list = bookLocal.queryBookSupport();
	}

	public void testNotSupport() {
		bookTranLocal.testNotSupport();
	}

	public void testMadatory() {
		bookTranLocal.testMandatory();
	}

	public void testNever() {
		bookTranLocal.testNever();
	}

}
