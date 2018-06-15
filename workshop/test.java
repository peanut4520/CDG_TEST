package workshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class test {
	private static final String URL_CONNECTION = "jdbc:mysql://localhost:3306/trainlab";
	private static final String USERNAME = "root";
	private static final String PASSWARD = "root";

	public static void main(String[] args) {
		final String sql = "SELECT * FROM trainlab.book WHERE title = 'Expert in Java'";
		List<Book> empList = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(URL_CONNECTION, USERNAME, PASSWARD);
				Statement stm = con.createStatement();
				ResultSet rs = stm.executeQuery(sql);) {

			while (rs.next()) {
				Book emp = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(4));
				empList.add(emp);
			}
			for (Book em1 : empList) {
				System.out.println(em1.toString());
			}

		} catch (SQLException e) {
			System.out.println(e);

		}

	}

}
