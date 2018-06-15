package workshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class prepareTest {
	private static final String URL_CONNECTION = "jdbc:mysql://localhost:3306/trainlab";
	private static final String USERNAME = "root";
	private static final String PASSWARD = "root";

	public static void main(String[] args) {
		// Book b = new Book(1444, "UP2P", "D", 1000, 11);
		// deleteBook(Arrays.asList(b));
		// Account A = new Account("5555","NN",1000);
		queryAccountInfoByAcctNoe("5555");
		// queryBookByTitle();
		// insertBook(Arrays.asList(b));
	}

	public static void queryBookByTitle() {
		String s1 = " WHERE title = 'Oracle DBA'";
		final String sql = "SELECT * FROM trainlab.book";
		List<Book> bookList = new ArrayList<Book>();

		try (Connection con = DriverManager.getConnection(URL_CONNECTION, USERNAME, PASSWARD);
				PreparedStatement stm = con.prepareStatement(sql);) {
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					Book emp = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(4));
					bookList.add(emp);
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		for (Book book : bookList) {
			System.out.println(book);
		}
	}

	public static void queryBookByCondition(Book bookCondition) {

		final StringBuilder sql = new StringBuilder(
				"select if title,author,publication_year,unit_price from a book where 1=1 ");
		List<Object> param = new ArrayList<>();

		if (bookCondition.getTitle() != null) {
			sql.append(" and title = ?");
			param.add(bookCondition.getTitle());
		}
		if (bookCondition.getAuthor() != null) {
			sql.append(" and author = ?");
			param.add(bookCondition.getAuthor());
		}
		if (bookCondition.getPublication_year() != null) {
			sql.append(" and publication_year = ?");
			param.add(bookCondition.getPublication_year());
		}
		if (bookCondition.getUnit_price() != 0) {
			sql.append(" and unit_price between ? and ?");
			param.add(bookCondition.getUnit_price());
		}

	}

	public static void insertBook(List<Book> bookList) {

		final String sql = "INSERT INTO BOOK(id, title, author, publication_year, unit_price)" + " VALUES (?,?,?,?,?)";

		try (Connection con = DriverManager.getConnection(URL_CONNECTION, USERNAME, PASSWARD);
				PreparedStatement stm = con.prepareStatement(sql);) {

			for (Book book : bookList) {
				stm.setInt(1, book.getId());
				stm.setString(2, book.getTitle());
				stm.setString(3, book.getAuthor());
				stm.setInt(4, book.getPublication_year());
				stm.setInt(5, book.getUnit_price());
				stm.executeUpdate();
			}

		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public static void updateBook(List<Book> bookList) {
		System.out.println(bookList.get(0).getId().toString());
		String s = bookList.get(0).getId().toString();

		final String sql = "UPDATE BOOK set id =?, title = ?, author = ?, Publication_year =? , Unit_price = ? where id = "
				+ s;

		try (Connection con = DriverManager.getConnection(URL_CONNECTION, USERNAME, PASSWARD);
				PreparedStatement stm = con.prepareStatement(sql);) {

			for (Book book : bookList) {
				stm.setInt(1, book.getId());
				stm.setString(2, book.getTitle());
				stm.setString(3, book.getAuthor());
				stm.setInt(4, book.getPublication_year());
				stm.setInt(5, book.getUnit_price());
				stm.executeUpdate();
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	public static void deleteBook(List<Book> bookList) {
		System.out.println(bookList.get(0).getId().toString());
		String s = bookList.get(0).getId().toString();

		final String sql = "delete from book where id = ?";

		try (Connection con = DriverManager.getConnection(URL_CONNECTION, USERNAME, PASSWARD);
				PreparedStatement stm = con.prepareStatement(sql);) {

			for (Book book : bookList) {
				stm.setInt(1, book.getId());
				stm.executeUpdate();
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	public static void queryAccountInfoByAcctNoe(String str) {

		String s1 = " WHERE acct_no = " + str;
		final String sql = "SELECT * FROM trainlab.Account"+s1;
		List<Account> accList = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(URL_CONNECTION, USERNAME, PASSWARD);
				PreparedStatement stm = con.prepareStatement(sql);) {
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					Account emp = new Account(rs.getString(1), rs.getString(2), rs.getInt(3));
					accList.add(emp);

				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		for (Account acc : accList) {
			System.out.println(acc);
		}
	}
	
	public static void queryAccountInfoByCondition(String str) {

		String s1 = " WHERE acct_no = " + str;
		final String sql = "SELECT * FROM trainlab.Account"+s1;
		List<Account> accList = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(URL_CONNECTION, USERNAME, PASSWARD);
				PreparedStatement stm = con.prepareStatement(sql);) {
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					Account emp = new Account(rs.getString(1), rs.getString(2), rs.getInt(3));
					accList.add(emp);

				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		for (Account acc : accList) {
			System.out.println(acc);
		}
	}

}
