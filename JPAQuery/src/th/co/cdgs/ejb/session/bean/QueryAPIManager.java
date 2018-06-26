package th.co.cdgs.ejb.session.bean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import th.co.cdgs.jpa.entity.City;
import th.co.cdgs.jpa.entity.Country;

/**
 * Session Bean implementation class QueryManager
 */
@Stateless
@LocalBean
public class QueryAPIManager implements QueryAPILocal {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Country> queryCountry() {
		final String jpql = "select c from Country c";
		Query query = entityManager.createQuery(jpql);
		List<Country> result = query.getResultList();
		return result;
	}

	@Override
	public List<Country> queryCountryByName(String name) {
		final String jpql = "select c from Country c where c.countryName = :countryName";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("countryName", name);
		List<Country> result = query.getResultList();
		return result;
	}

	@Override
	public void queryJPQL() {
		final String jpql = "select c.country.countryId, count(c.cityId) from City c group by c.country.countryId";
		Query query = entityManager.createQuery(jpql);
		List<Object[]> results = query.getResultList();
		for (Object[] obj : results) {
			System.out.println(" country_id : " + obj[0] + " count city = : " + obj[1]);
		}
	}

	@Override
	public void queryJoin() {// ทดสอบเปลี่ยนเป็น left join ด้วย
		String jpql = "SELECT c.countryName, ci.cityName FROM Country c INNER JOIN c.cities ci";
		Query query = entityManager.createQuery(jpql);
		List<Object[]> results = query.getResultList();
		int count = 1;
		for (Object[] obj : results) {
			System.out.println(count++ + " country :  " + obj[0] + "   city : " + obj[1]);
		}
	}

	public List<Country> queryCountryJoinFecth() {
		String jpql = "select c from Country c join fetch c.cities ci";
		Query query = entityManager.createQuery(jpql);
		List<Country> results = query.getResultList();
		int count = 1;
		for (Country country : results) {
			System.out.println(count++ + " country : " + country.getCountryName());
		}
		return results;
	}

	public List<City> queryCityJoinFecth() {
		String jpql = "select ct from City ct join ct.country c";
		Query query = entityManager.createQuery(jpql);
		List<City> results = query.getResultList();
		return results;
	}

	public City queryNativeSQL(String name) {// ถ้าใช้ resultClass ต้อง select ให้ครบ
		// String sql = "select * from city where city_name = :cityName";
		String sql = "select city_id, city_name from city where city_name = :cityName";
		// Query query = entityManager.createNativeQuery(sql, City.class);
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("cityName", name);

		// City city = (City) query.getSingleResult();
		Object[] objects = (Object[]) query.getSingleResult();
		City city = new City();
		city.setCityId(((Short) objects[0]).longValue());
		city.setCityName((String) objects[1]);
		return city;
	}
}
