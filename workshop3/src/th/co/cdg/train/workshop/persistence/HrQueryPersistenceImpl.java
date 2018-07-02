package th.co.cdg.train.workshop.persistence;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import th.co.cdg.train.workshop.bean.DepartmentBean;
import th.co.cdg.train.workshop.bean.EmployeeBean;
import th.co.cdg.train.workshop.bean.JobTitleBean;
import th.co.cdg.train.workshop.entity.Department;
import th.co.cdg.train.workshop.entity.Employee;
import th.co.cdg.train.workshop.entity.JobTitle;
import th.co.cdg.train.workshop.entity.Skill;

/**
 * Session Bean implementation class HrQueryPersistenceImpl
 */
@Stateless
@LocalBean
public class HrQueryPersistenceImpl implements HrQueryPersistenceLocal {

	@PersistenceContext(unitName = "trainworkshop")
	private EntityManager em;

	@Override
	public List<Department> queryDepartment() {

		final String sql = "select department_code, department_name from department order by department_code";

		Query query = em.createNativeQuery(sql, Department.class);

		return query.getResultList();
	}

	@Override
	public List<JobTitle> queryJobTitle() {

		final String sql = "select job_title_code, job_title_name, job_type from job_title order by job_title_code";

		Query query = em.createNativeQuery(sql, JobTitle.class);

		return query.getResultList();
	}

	@Override
	public List<Employee> queryEmployeeByCondition(EmployeeBean employeeBean) {

		if (employeeBean == null || ((employeeBean.getDepartmentBean() == null
				|| employeeBean.getDepartmentBean().getDepartmentCode() == null
				|| "".equals(employeeBean.getDepartmentBean().getDepartmentCode()))
				&& (employeeBean.getJobTitleBean() == null || employeeBean.getJobTitleBean().getJobTitleCode() == null
						|| "".equals(employeeBean.getJobTitleBean().getJobTitleCode()))
				&& (employeeBean.getJobTitleBean() == null || employeeBean.getJobTitleBean().getJobType() == null
						|| "".equals(employeeBean.getJobTitleBean().getJobType()))
				&& (employeeBean.getFirstName() == null || "".equals(employeeBean.getFirstName()))
				&& (employeeBean.getLastName() == null || "".equals(employeeBean.getLastName()))
				&& (employeeBean.getGender() == null || "".equals(employeeBean.getGender())))) {
			throw new IllegalArgumentException("The all parameter is blank or null.");
		}

		final StringBuilder jpql = new StringBuilder();
		// jpql.append("select e from Employee e where 1=1 ");
		jpql.append("select e from Employee e join e.department d where 1=1 ");

		DepartmentBean departmentBean = employeeBean.getDepartmentBean();
		if (departmentBean != null && departmentBean.getDepartmentCode() != null
				&& !"".equals(departmentBean.getDepartmentCode())) {
			// jpql.append("and e.department.departmentCode = :departmentCode ");
			jpql.append("and d.departmentCode = :departmentCode ");
		}

		JobTitleBean jobTitleBean = employeeBean.getJobTitleBean();
		if (jobTitleBean != null && jobTitleBean.getJobTitleCode() != null
				&& !"".equals(jobTitleBean.getJobTitleCode())) {
			jpql.append("and e.jobTitle.jobTitleCode = :jobTitleCode ");
		}
		if (jobTitleBean != null && jobTitleBean.getJobType() != null && !"".equals(jobTitleBean.getJobType())) {
			jpql.append("and e.jobTitle.jobType = :jobType ");
		}

		if (employeeBean.getFirstName() != null && !"".equals(employeeBean.getFirstName())) {
			jpql.append("and e.firstName like :firstName ");
		}

		if (employeeBean.getLastName() != null && !"".equals(employeeBean.getLastName())) {
			jpql.append("and e.lastName like :lastName ");
		}

		if (employeeBean.getGender() != null && !"".equals(employeeBean.getGender())) {
			jpql.append("and e.gender = :gender ");
		}

		Query query = em.createQuery(jpql.toString());

		if (departmentBean != null && departmentBean.getDepartmentCode() != null
				&& !"".equals(departmentBean.getDepartmentCode())) {
			query.setParameter("departmentCode", departmentBean.getDepartmentCode());
		}

		if (jobTitleBean != null && jobTitleBean.getJobTitleCode() != null
				&& !"".equals(jobTitleBean.getJobTitleCode())) {
			query.setParameter("jobTitleCode", jobTitleBean.getJobTitleCode());
		}
		if (jobTitleBean != null && jobTitleBean.getJobType() != null && !"".equals(jobTitleBean.getJobType())) {
			query.setParameter("jobType", jobTitleBean.getJobType());
		}

		if (employeeBean.getFirstName() != null && !"".equals(employeeBean.getFirstName())) {
			query.setParameter("firstName", employeeBean.getFirstName() + "%");
		}

		if (employeeBean.getLastName() != null && !"".equals(employeeBean.getLastName())) {
			query.setParameter("lastName", employeeBean.getLastName() + "%");
		}

		if (employeeBean.getGender() != null && !"".equals(employeeBean.getGender())) {
			query.setParameter("gender", employeeBean.getGender());
		}
		return query.getResultList();
	}

	@Override
	public Employee queryEmployeeById(String employeeId) {
		if (employeeId == null || "".equals(employeeId)) {
			throw new IllegalArgumentException("The employeeId parameter is blank or null.");
		}

		final String sql = "select e from Employee e where e.employeeId = :employeeId ";

		Query query = em.createQuery(sql);
		query.setParameter("employeeId", employeeId);

		return (Employee) query.getSingleResult();
	}

	@Override
	public Employee queryEmployeeAndSkillByEmployeeId(String employeeId) {
		if (employeeId == null || "".equals(employeeId)) {
			throw new IllegalArgumentException("The employeeId parameter is blank or null.");
		}

		final String sql = "select e from Employee e" + " left join e.skills s" + " where e.employeeId = :employeeId ";

		Query query = em.createQuery(sql);
		query.setParameter("employeeId", employeeId);

		return (Employee) query.getSingleResult();
	}

	@Override
	public List<Skill> querySkillByEmployeeId(String employeeId) {

		final String sql = "select s from Skill s where s.employee.employeeId = :employeeId ";

		Query query = em.createQuery(sql);
		query.setParameter("employeeId", employeeId);

		return query.getResultList();
	}

	@Override
	public String queryNextEmployeeId() {

		String sql = "select coalesce(max(employee_id) + 1 , 1) from employee";

		Query query = em.createNativeQuery(sql);

		Object o = query.getSingleResult();
		String unpadded = String.valueOf(((Number) o).intValue());
		String padded = "00000".substring(unpadded.length()) + unpadded;

		return padded;
	}

}
