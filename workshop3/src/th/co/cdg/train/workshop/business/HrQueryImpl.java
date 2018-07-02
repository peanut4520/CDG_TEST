package th.co.cdg.train.workshop.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import th.co.cdg.train.workshop.bean.DepartmentBean;
import th.co.cdg.train.workshop.bean.EmployeeBean;
import th.co.cdg.train.workshop.bean.JobTitleBean;
import th.co.cdg.train.workshop.bean.SkillBean;
import th.co.cdg.train.workshop.entity.Department;
import th.co.cdg.train.workshop.entity.Employee;
import th.co.cdg.train.workshop.entity.JobTitle;
import th.co.cdg.train.workshop.entity.Skill;
import th.co.cdg.train.workshop.persistence.HrQueryPersistenceLocal;

/**
 * Session Bean implementation class HrQueryImpl
 */
@Stateless
@LocalBean
public class HrQueryImpl implements HrQueryRemote, HrQueryLocal {

	@EJB
	private HrQueryPersistenceLocal hrQueryPersistenceLocal;
	
	@Override
	public List<DepartmentBean> queryDepartment() {

		List<Department> departments = hrQueryPersistenceLocal.queryDepartment();
		
		if ( departments != null && !departments.isEmpty() ) {
			
			List<DepartmentBean> departmentBeans = new ArrayList<DepartmentBean>();
			for (Department department : departments) {
				DepartmentBean departmentBean = new DepartmentBean();
				departmentBean.setDepartmentCode(department.getDepartmentCode());
				departmentBean.setDepartmentName(department.getDepartmentName());
				
				departmentBeans.add(departmentBean);
			}
			
//			for (int i = 0; i < departments.size(); i++) {
//				
//				Department dept = departments.get(i);
//				
//				DepartmentBean departmentBean = new DepartmentBean();
//				departmentBean.setDepartmentCode(dept.getDepartmentCode());
//				departmentBean.setDepartmentName(dept.getDepartmentName());
//				
//				departmentBeans.add(departmentBean);
//			}
			
			return departmentBeans;
		}
		
		return null;
	}

	@Override
	public List<JobTitleBean> queryJobTitle() {

		List<JobTitle> jobTitles = hrQueryPersistenceLocal.queryJobTitle();
		
		if ( jobTitles != null && !jobTitles.isEmpty() ) {
			
			List<JobTitleBean> jobTitleBeans = new ArrayList<JobTitleBean>();
			for (JobTitle jobTitle : jobTitles) {
				JobTitleBean jobTitleBean = new JobTitleBean();
				jobTitleBean.setJobTitleCode(jobTitle.getJobTitleCode());
				jobTitleBean.setJobTitleName(jobTitle.getJobTitleName());
				jobTitleBean.setJobType(jobTitle.getJobType());
				
				jobTitleBeans.add(jobTitleBean);
			}
			
			return jobTitleBeans;
		}
		return null;
	}

	@Override
	public List<EmployeeBean> queryEmployeeByCondition(EmployeeBean employeeBean) {

		List<Employee> employees = hrQueryPersistenceLocal.queryEmployeeByCondition(employeeBean);
		
		if ( employees != null && !employees.isEmpty() ) {
			
			final List<EmployeeBean> employeeBeans = new ArrayList<EmployeeBean>();
			for (Employee employee : employees) {
				
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
				
				employeeBeans.add(empBean);
			}
			
			return employeeBeans;
		}
		
		return null;
	}

	@Override
	public EmployeeBean queryEmployeeAndSkillById(String employeeId) {
		
		EmployeeBean employeeBean = queryEmployeeById(employeeId);
		if ( employeeBean != null ) {
			
			List<SkillBean> skillBeans = querySkillByEmployeeId(employeeId);
			
			employeeBean.setSkillBeans(skillBeans);
			
			return employeeBean;
		}
		
		return null;
	}
	
	@Override
	public EmployeeBean queryEmployeeAndSkillByEmployeeId(String employeeId) {
		Employee employee = hrQueryPersistenceLocal.queryEmployeeAndSkillByEmployeeId(employeeId);
		
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
		
		
		if(!employee.getSkills().isEmpty()) {
			List<SkillBean> skillBeans = new ArrayList<SkillBean>();
			for (Skill skill : employee.getSkills()) {
				SkillBean skillBean = new SkillBean();
				skillBean.setSkillId(skill.getSkillId());
				skillBean.setSkillName(skill.getSkillName());
				skillBean.setSkillDesc(skill.getSkillDesc());
				
				skillBeans.add(skillBean);
			}
			empBean.setSkillBeans(skillBeans);
		}
		
		return empBean;
	}
	
	@Override
	public EmployeeBean queryEmployeeById(String employeeId) {

		Employee employee = hrQueryPersistenceLocal.queryEmployeeById(employeeId);
		
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

	@Override
	public List<SkillBean> querySkillByEmployeeId(String employeeId) {

		List<Skill> skills = hrQueryPersistenceLocal.querySkillByEmployeeId(employeeId);
		
		if ( skills != null && !skills.isEmpty() ) {
			
			List<SkillBean> skillBeans = new ArrayList<SkillBean>();
			for (Skill skill : skills) {
				SkillBean skillBean = new SkillBean();
				skillBean.setSkillId(skill.getSkillId());
				skillBean.setSkillName(skill.getSkillName());
				skillBean.setSkillDesc(skill.getSkillDesc());
				
				skillBeans.add(skillBean);
			}
			
			return skillBeans;
		}
		
		return null;
	}

}
