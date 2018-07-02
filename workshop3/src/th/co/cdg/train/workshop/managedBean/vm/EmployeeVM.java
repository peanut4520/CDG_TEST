package th.co.cdg.train.workshop.managedBean.vm;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class EmployeeVM {

	private String firstName;
	private String lastName;
	private String gender;
	private String departmentCode;
	private String jobTitleCode;
	private String jobType;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getJobTitleCode() {
		return jobTitleCode;
	}
	public void setJobTitleCode(String jobTitleCode) {
		this.jobTitleCode = jobTitleCode;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	
	public void reset() {
		setFirstName(null);
		setLastName(null);
		setJobTitleCode(null);
		setJobType(null);
		setDepartmentCode(null);
		setGender(null);
	}
	
}
