package th.co.cdg.train.workshop.bean;

import java.io.Serializable;

public class DepartmentBean implements Serializable {

	private static final long serialVersionUID = -7822395268847648395L;
	private String departmentCode;
	private String departmentName;
	
	public DepartmentBean() {
		super();
	}
	
	public DepartmentBean(String departmentCode, String departmentName) {
		super();
		this.departmentCode = departmentCode;
		this.departmentName = departmentName;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
}
