package th.co.cdg.train.workshop.managedBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import th.co.cdg.train.workshop.bean.DepartmentBean;
import th.co.cdg.train.workshop.bean.EmployeeBean;
import th.co.cdg.train.workshop.bean.JobTitleBean;
import th.co.cdg.train.workshop.bean.SkillBean;
import th.co.cdg.train.workshop.business.HrBusinessLocal;
import th.co.cdg.train.workshop.business.HrQueryLocal;
import th.co.cdg.train.workshop.managedBean.vm.EmployeeEditVM;
import th.co.cdg.train.workshop.managedBean.vm.EmployeeVM;

@SessionScoped
@ManagedBean
public class EditController {

	@EJB
	private HrQueryLocal hrQueryLocal;

	@EJB
	private HrBusinessLocal hrBusinessLocal;

	private List<String> arrMale = Arrays.asList("MR");
	private List<String> arrFemale = Arrays.asList("MRS", "MISS");
	private List<String> genders = Arrays.asList("MR", "MRS", "MISS");

	public List<String> getGenders() {
		return genders;
	}

	public void setGenders(List<String> genders) {
		this.genders = genders;
	}

	@ManagedProperty(value = "#{employeeVM}")
	private EmployeeVM employeeVM;

	@ManagedProperty(value = "#{employeeEditVM}")
	private EmployeeEditVM employeeEditVM;

	private List<SkillBean> skillBeans;

	public HrQueryLocal getHrQueryLocal() {
		return hrQueryLocal;
	}

	public void setHrQueryLocal(HrQueryLocal hrQueryLocal) {
		this.hrQueryLocal = hrQueryLocal;
	}

	public HrBusinessLocal getHrBusinessLocal() {
		return hrBusinessLocal;
	}

	public void setHrBusinessLocal(HrBusinessLocal hrBusinessLocal) {
		this.hrBusinessLocal = hrBusinessLocal;
	}

	public List<String> getArrMale() {
		return arrMale;
	}

	public void setArrMale(List<String> arrMale) {
		this.arrMale = arrMale;
	}

	public List<String> getArrFemale() {
		return arrFemale;
	}

	public void setArrFemale(List<String> arrFemale) {
		this.arrFemale = arrFemale;
	}

	public List<SkillBean> getSkillBeans() {
		return skillBeans;
	}

	public void setSkillBeans(List<SkillBean> skillBeans) {
		this.skillBeans = skillBeans;
	}

	public EmployeeEditVM getEmployeeEditVM() {
		return employeeEditVM;
	}

	public void setEmployeeEditVM(EmployeeEditVM employeeEditVM) {
		this.employeeEditVM = employeeEditVM;
	}

	public EmployeeVM getEmployeeVM() {
		return employeeVM;
	}

	public void setEmployeeVM(EmployeeVM employeeVM) {
		this.employeeVM = employeeVM;
	}

	public void genarateTitleOption() {
		genders = new ArrayList<String>();
		if (employeeEditVM.getGender() == null) {
			genders.addAll(genders);
		} else if (employeeEditVM.getGender().equals("M")) {
			genders.addAll(arrMale);
		} else if (employeeEditVM.getGender().equals("F")) {
			genders.addAll(arrFemale);
		}

	}

	public void submit() {
		if (employeeEditVM != null) {

			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean.setEmployeeId(employeeEditVM.getEmployeeId());
			employeeBean.setTitle(employeeEditVM.getTitle());
			employeeBean.setGender(employeeEditVM.getGender());
			employeeBean.setFirstName(employeeEditVM.getFirstName());
			employeeBean.setLastName(employeeEditVM.getLastName());
			employeeBean.setAddress(employeeEditVM.getAddress());
			employeeBean.setDepartmentBean(new DepartmentBean(employeeEditVM.getDepartmentCode(), null));
			employeeBean.setJobTitleBean(new JobTitleBean(employeeEditVM.getJobTitleCode(), null, null));

			if (employeeEditVM.getEmployeeId() == null || employeeEditVM.getEmployeeId().isEmpty()) {
				employeeBean = hrBusinessLocal.insertEmployee(employeeBean);
			} else {
				employeeBean = hrBusinessLocal.updateEmployee(employeeBean);
			}

			employeeEditVM.setEmployeeId(employeeBean.getEmployeeId());
			employeeEditVM.setTitle(employeeBean.getTitle());
			employeeEditVM.setGender(employeeBean.getGender());
			employeeEditVM.setFirstName(employeeBean.getFirstName());
			employeeEditVM.setLastName(employeeBean.getLastName());
			employeeEditVM.setAddress(employeeBean.getAddress());
			employeeEditVM.setDepartmentCode(
					employeeBean.getDepartmentBean() != null ? employeeBean.getDepartmentBean().getDepartmentCode()
							: null);
			employeeEditVM.setJobTitleCode(
					employeeBean.getJobTitleBean() != null ? employeeBean.getJobTitleBean().getJobTitleCode() : null);
		}

	}

	public void clear() {
		employeeEditVM.reset();
		setSkillBeans(null);
	}

}
