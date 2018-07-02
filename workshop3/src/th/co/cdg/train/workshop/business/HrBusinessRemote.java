package th.co.cdg.train.workshop.business;

import javax.ejb.Remote;

import th.co.cdg.train.workshop.bean.EmployeeBean;

@Remote
public interface HrBusinessRemote {

	public EmployeeBean insertEmployee(EmployeeBean employee);

	public EmployeeBean updateEmployee(EmployeeBean employee);

	public void deleteEmployee(String employeeId);
	
}
