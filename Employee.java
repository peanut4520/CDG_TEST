import java.util.Date;

public class Employee {
	private Integer employeeId;
	private String name;
	private String gender;
	private Integer salary;
	private Date startDate;
	private String positionName;

	public Employee(Integer employeeId, String name, String gender, Integer salary, Date startDate,
			String positionName) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.gender = gender;
		this.salary = salary;
		this.startDate = startDate;
		this.positionName = positionName;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", gender=" + gender + ", salary=" + salary
				+ ", startDate=" + startDate + ", positionName=" + positionName + "]";
	}

	public Employee() {
	}

	
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

}
