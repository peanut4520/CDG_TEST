package th.co.cdgs.ejb.session.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import th.co.cdgs.jpa.entity.Book;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BookTranManager implements BookTranLocal {

	@EJB
	private BookLocal bookLocal;

	@EJB
	private BookTranLocal bookTranLocal;

	@PersistenceContext(unitName = "trainlab")
	private EntityManager em;

	@Override
	public void testRequire() {
		try {
			Book book1 = new Book();
			book1.setId(9L);
			book1.setTitle("Book1");
			book1.setAuthor("Tony Stark");
			bookLocal.insertBook(book1);

			Book book2 = new Book();
			book2.setId(10L);
			book2.setTitle("No");
			book2.setAuthor("Thor");
			bookLocal.insertBook(book2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void testRequireNew() {
		Book book1 = new Book();
		book1.setId(19L);
		book1.setTitle("Book1");
		book1.setAuthor("Tony Stark");
		bookLocal.insertBook(book1);

		Book book2 = new Book();
		book2.setId(20L);
		book2.setTitle("Book2");
		book2.setAuthor("Hulk");
		bookLocal.insertBookRequiresNew(book2);

		Book book3 = new Book();
		book3.setId(21L);
		book3.setTitle(null);
		book3.setAuthor("Steve Rogers");
		bookLocal.insertBook(book3);
	}

	@Override
	// @TransactionAttribute(TransactionAttributeType.SUPPORTS) ทดสอบ 2 แบบ
	public void testSupport() {
		Book book1 = new Book();
		book1.setId(29L);
		book1.setTitle("Book1");
		bookLocal.insertBook(book1);

		Book book2 = new Book();
		book2.setId(30L);
		book2.setTitle("Book Support");
		bookLocal.insertBookSupport(book2);

		Book book3 = new Book();
		book3.setId(31L);
		book3.setTitle("Book3");
		bookLocal.insertBook(book3);
	}

	@Override
	public void testNotSupport() {
		Book book1 = new Book();
		book1.setId(40L);
		book1.setTitle("Book1");
		bookLocal.insertBook(book1);

		// not support จะมองไม่เห็น book1 ที่ insert ไป ทดสอบเปลี่ยนเป็นแบบ support แล้ว
		// select ดูที่ toad ด้วย
		List<Book> bookList = bookLocal.queryBookNotSupport();
		for (Book book : bookList) {
			System.out.println(" book : " + book.getTitle());
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void testMandatory() {
		Book book1 = new Book();
		book1.setId(50L);
		book1.setTitle("Book1");
		bookLocal.insertBook(book1);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NEVER)
	// ทดสอบแบบ require เรียก never ก่อน
	public void testNever() {
		List<Book> list = bookLocal.queryBookNever();
		for (Book book : list) {
			System.out.println(" book : " + book.getTitle());
		}
	}

}
