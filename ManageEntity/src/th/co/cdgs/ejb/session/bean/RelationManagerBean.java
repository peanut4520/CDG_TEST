package th.co.cdgs.ejb.session.bean;

import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import th.co.cdgs.jpa.entity.City;
import th.co.cdgs.jpa.entity.Country;

@Stateless
@LocalBean
public class RelationManagerBean implements RelationManagerBeanLocal {

	@PersistenceContext(unitName = "trainlab")
	private EntityManager entityManager;

	@Override
	public Country findCountryById(Long countryId) {
		Country country = entityManager.find(Country.class, countryId);
		/*Set<City> cities = country.getCities();
		cities.iterator().next();*/

		// cities.forEach(city -> city.getCity());
		// @OneToMany
		// FetchType.LAZY ต้องเข้าถึงObject ใน set @OneToMany (In persistenct context)
		// จะทำการ select by pk ตามอีกครั้ง
		// FetchType.EAGER จะgenerate SQL left outer join
		return country;
	}

	@Override
	public City findCityById(Long cityId) {
		City city = entityManager.find(City.class, cityId);

		// @ManyToOne FetchType.EAGER จะgenerate SQL inner join join
		return city;
	}

}
