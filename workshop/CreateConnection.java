package workshop;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CreateConnection {
	private static final String URL_CONNECTION = "jdbc:mysql://localhost:3306/trainlab";
	private static final String USERNAME = "root";
	private static final String PASSWARD = "root";

	public CreateConnection() {
		// TODO Auto-generated constructor stub
	}

	// Statement stm = con.createStatement();
	// ResultSet rs = stm.executeQuery(sql);)
	public static void main(String[] args) {
		String nameCondition = "%y";
		//final String sql ="INSERT INTO EMPLOYEE (NAME SALARY GENNDER START_DATE POSITION_NAME)";
		final String sql = "SELECT * FROM trainlab.book";
		List<Book> empList = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(URL_CONNECTION, USERNAME, PASSWARD);
				PreparedStatement stm =  con.prepareStatement(sql);)

		{
			stm.setString(1, nameCondition); //ตัวที่ 1 
			try (ResultSet rs = stm.executeQuery()) {

				while (rs.next()) {
					Book emp = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(4));
					empList.add(emp);
				}
				for (Book em1 : empList) {
					System.out.println(em1);
				}
			}

		} catch (SQLException e) {
			System.out.println(e);

		}

	}

}
