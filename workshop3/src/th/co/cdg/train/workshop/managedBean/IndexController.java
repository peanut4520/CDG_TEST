package th.co.cdg.train.workshop.managedBean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import th.co.cdg.train.workshop.bean.DepartmentBean;
import th.co.cdg.train.workshop.bean.EmployeeBean;
import th.co.cdg.train.workshop.bean.JobTitleBean;
import th.co.cdg.train.workshop.business.HrBusinessLocal;
import th.co.cdg.train.workshop.business.HrQueryLocal;
import th.co.cdg.train.workshop.managedBean.vm.EmployeeEditVM;
import th.co.cdg.train.workshop.managedBean.vm.EmployeeVM;

@SessionScoped
@ManagedBean
public class IndexController {

	@EJB
	private HrQueryLocal hrQueryLocal;
	
	@EJB
	private HrBusinessLocal hrBusinessLocal;
	
	@ManagedProperty(value = "#{employeeVM}")
	private EmployeeVM employeeVM;

	@ManagedProperty(value = "#{employeeEditVM}")
	private EmployeeEditVM employeeEditVM;

	@ManagedProperty(value = "#{editController}")
	private EditController editController;
	
	private List<DepartmentBean> departmentBeans;
	private List<JobTitleBean> jobTitleBeans;
	
	private List<EmployeeBean> employeeBeans;
	
	public EmployeeVM getEmployeeVM() {
		return employeeVM;
	}
	public void setEmployeeVM(EmployeeVM employeeVM) {
		this.employeeVM = employeeVM;
	}
	
	public EmployeeEditVM getEmployeeEditVM() {
		return employeeEditVM;
	}
	public void setEmployeeEditVM(EmployeeEditVM employeeEditVM) {
		this.employeeEditVM = employeeEditVM;
	}
	
	public List<DepartmentBean> getDepartmentBeans() {
		if ( departmentBeans == null ) {
			departmentBeans = hrQueryLocal.queryDepartment();
		}
		return departmentBeans;
	}
	public void setDepartmentBeans(List<DepartmentBean> departmentBeans) {
		this.departmentBeans = departmentBeans;
	}
	
	public List<JobTitleBean> getJobTitleBeans() {
		if ( jobTitleBeans == null ) {
			jobTitleBeans = hrQueryLocal.queryJobTitle();
		}
		return jobTitleBeans;
	}
	public void setJobTitleBeans(List<JobTitleBean> jobTitleBeans) {
		this.jobTitleBeans = jobTitleBeans;
	}
	
	public List<EmployeeBean> getEmployeeBeans() {
		return employeeBeans;
	}
	public void setEmployeeBeans(List<EmployeeBean> employeeBeans) {
		this.employeeBeans = employeeBeans;
	}
	
	
	
	public EditController getEditController() {
		return editController;
	}
	public void setEditController(EditController editController) {
		this.editController = editController;
	}
	public void search() {
		final EmployeeBean employeeBean = new EmployeeBean();
		
		final EmployeeVM empVM = getEmployeeVM();
		if ( empVM != null ) {
			employeeBean.setFirstName(empVM.getFirstName());
			employeeBean.setLastName(empVM.getLastName());
			employeeBean.setGender(empVM.getGender());
			employeeBean.setDepartmentBean(new DepartmentBean(empVM.getDepartmentCode(), null));
			employeeBean.setJobTitleBean(new  JobTitleBean(empVM.getJobTitleCode(), null, empVM.getJobType()));
		}
		
		final List<EmployeeBean> employeeBeans = hrQueryLocal.queryEmployeeByCondition(employeeBean);
		setEmployeeBeans(employeeBeans);
	}
	
	public void clear() {
		employeeVM.reset();
		setEmployeeBeans(null);
	}
	
	public String gotoIndex() {
		employeeVM.reset();
		setEmployeeBeans(null);
		return "index";
	}
	
	public String gotoEditModeAdd() {
		getEmployeeEditVM().reset();
		getEditController().setSkillBeans(null);
		return "edit";
	}
	
	public String gotoEditModeEdit(EmployeeBean employeeBean) {
		
		EmployeeBean empBean = hrQueryLocal.queryEmployeeAndSkillById(employeeBean.getEmployeeId());
		
		EmployeeEditVM employeeEditVM = getEmployeeEditVM();
		employeeEditVM.setEmployeeId(empBean.getEmployeeId());
		employeeEditVM.setTitle(empBean.getTitle());
		employeeEditVM.setGender(empBean.getGender());
		employeeEditVM.setFirstName(empBean.getFirstName());
		employeeEditVM.setLastName(empBean.getLastName());
		employeeEditVM.setAddress(empBean.getAddress());
		if ( empBean.getDepartmentBean() != null ) {
			employeeEditVM.setDepartmentCode(empBean.getDepartmentBean().getDepartmentCode());
		}
		if ( empBean.getJobTitleBean() != null ) {
			employeeEditVM.setJobTitleCode(empBean.getJobTitleBean().getJobTitleCode());
		}
		if ( empBean.getSkillBeans() != null && !empBean.getSkillBeans().isEmpty()) {
			editController.setSkillBeans(empBean.getSkillBeans());
		}
		
		return "edit";
	}
	
	public void remove(EmployeeBean employeeBean) {
		hrBusinessLocal.deleteEmployee(employeeBean.getEmployeeId());
	}
	
}
