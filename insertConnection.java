
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class insertConnection {
	private static final String URL_CONNECTION = "jdbc:mysql://localhost:3306/EMPLOYEE";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	public static void main(String[] args) {
		Employee emp1 = new Employee(null, "SalaryMan", "F", 1000, null, "salary man");
		addEmployee(Arrays.asList(emp1));
	}

	private static void addEmployee(List<Employee> em) {
		final String sql = "INSERT INTO EMPLOYEE (NAME,SALARY,GENDER,START_DATE,POSITION_NAME)" + " VALUES (?,?,?,?,?)";
		try (Connection con = DriverManager.getConnection(URL_CONNECTION, USERNAME, PASSWORD);
				PreparedStatement stm = con.prepareStatement(sql);) {

			for (Employee employee : em) {
				stm.setString(1, employee.getName());
				stm.setInt(2, employee.getSalary());
				stm.setString(3, employee.getGender());
				stm.setDate(4,
						employee.getStartDate() == null ? null : new java.sql.Date(employee.getStartDate().getTime()));
				stm.setString(5, employee.getPositionName());
				stm.executeUpdate();
			}

		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}