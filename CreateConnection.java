import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CreateConnection {
	private static final String URL_CONNECTION = "jdbc:mysql://localhost:3306/EMPLOYEE";
	private static final String USERNAME = "root";
	private static final String PASSWARD = "root";

	public CreateConnection() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		final String sql = "SELECT EMPLOYEE_ID," + " NAME , SALARY, GENDER," + " START_DATE, POSITION_NAME"
				+ " FROM EMPLOYEE Where Name Like '%Catty'";
		List<Employee> empList = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(URL_CONNECTION, USERNAME, PASSWARD);
				Statement stm = con.createStatement();
				ResultSet rs = stm.executeQuery(sql);) {

			while (rs.next()) {
				Employee emp = new Employee(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getInt(3), rs.getDate(5), rs.getString(6));empList.add(emp);
			}
			for(Employee em1 : empList) {
				System.out.println(em1);
			}

		} catch (SQLException e) {
			System.out.println(e);

		}

	}

}
