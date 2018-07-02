package th.co.cdg.train.workshop.persistence;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import th.co.cdg.train.workshop.entity.Employee;

/**
 * Session Bean implementation class HrPersistenceImpl
 */
@Stateless
@LocalBean
public class HrPersistenceImpl implements HrPersistenceLocal {

	@PersistenceContext(unitName = "trainworkshop")
	private EntityManager em;
	
	@EJB
	private HrQueryPersistenceLocal hrQueryPersistenceLocal;
	
	@Override
	public Employee insertEmployee(Employee employee) {
		
		String nextEmployeeId = hrQueryPersistenceLocal.queryNextEmployeeId();
		employee.setEmployeeId(nextEmployeeId);
		
		em.persist(employee);
		
		return employee;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return em.merge(employee);
	}

	@Override
	public void deleteEmployee(String employeeId) {
		em.remove(em.find(Employee.class, employeeId));
	}

}
