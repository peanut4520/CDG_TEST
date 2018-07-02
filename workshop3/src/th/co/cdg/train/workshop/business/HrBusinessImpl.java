package th.co.cdg.train.workshop.business;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import th.co.cdg.train.workshop.bean.DepartmentBean;
import th.co.cdg.train.workshop.bean.EmployeeBean;
import th.co.cdg.train.workshop.bean.JobTitleBean;
import th.co.cdg.train.workshop.entity.Department;
import th.co.cdg.train.workshop.entity.Employee;
import th.co.cdg.train.workshop.entity.JobTitle;
import th.co.cdg.train.workshop.persistence.HrPersistenceLocal;

/**
 * Session Bean implementation class HrBusinessImpl
 */
@Stateless
@LocalBean
public class HrBusinessImpl implements HrBusinessRemote, HrBusinessLocal {

	@EJB
	private HrPersistenceLocal hrPersistenceLocal;
	
	@Override
	public EmployeeBean insertEmployee(EmployeeBean employeeBean) {
		
		if ( employeeBean != null ) {
			
			Employee employee = transformEmployeeBeanToEmployee(employeeBean);
			
			employee = hrPersistenceLocal.insertEmployee(employee);

			EmployeeBean empBean = transformEmployeeToEmployeeBean(employee);
			
			return empBean;
		}
		
		return null;
	}

	@Override
	public EmployeeBean updateEmployee(EmployeeBean employeeBean) {
		
		if ( employeeBean != null ) {
			
			Employee employee = transformEmployeeBeanToEmployee(employeeBean);
			
			employee = hrPersistenceLocal.updateEmployee(employee);

			EmployeeBean empBean = transformEmployeeToEmployeeBean(employee);
			
			return empBean;
		}
		
		return null;
	}

	@Override
	public void deleteEmployee(String employeeId) {
		hrPersistenceLocal.deleteEmployee(employeeId);
	}

	private Employee transformEmployeeBeanToEmployee(EmployeeBean employeeBean) {
		if ( employeeBean != null ) {
			Employee employee = new Employee();
			employee.setEmployeeId(employeeBean.getEmployeeId());
			employee.setTitle(employeeBean.getTitle());
			employee.setGender(employeeBean.getGender());
			employee.setFirstName(employeeBean.getFirstName());
			employee.setLastName(employeeBean.getLastName());
			employee.setAddress(employeeBean.getAddress());
			if ( employeeBean.getDepartmentBean() != null ) {
				employee.setDepartment(new Department(employeeBean.getDepartmentBean().getDepartmentCode(), null));
			}
			if ( employeeBean.getJobTitleBean() != null ) {
				employee.setJobTitle(new JobTitle(employeeBean.getJobTitleBean().getJobTitleCode(), null, null));
			}
			
			return employee;
		}
		
		return null;
	}

	private EmployeeBean transformEmployeeToEmployeeBean(Employee employee) {
		if ( employee != null ) {

			EmployeeBean empBean = new EmployeeBean();
			empBean.setEmployeeId(employee.getEmployeeId());
			empBean.setTitle(employee.getTitle());
			empBean.setFirstName(employee.getFirstName());
			empBean.setLastName(employee.getLastName());
			empBean.setAddress(employee.getAddress());
			empBean.setGender(employee.getGender());
			
			if ( employee.getDepartment() != null ) {
				empBean.setDepartmentBean(new DepartmentBean(employee.getDepartment().getDepartmentCode(), employee.getDepartment().getDepartmentName()));
			}
			
			if ( employee.getJobTitle() != null ) {
				empBean.setJobTitleBean(new JobTitleBean(employee.getJobTitle().getJobTitleCode(), employee.getJobTitle().getJobTitleName(), employee.getJobTitle().getJobType() ));
			}
			
			return empBean;
		}
		
		return null;
	}
	
}
