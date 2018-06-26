package th.co.cdgs.view;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import th.co.cdgs.ejb.session.bean.RelationManagerBeanLocal;
import th.co.cdgs.jpa.entity.City;
import th.co.cdgs.jpa.entity.Country;

@ManagedBean
public class RelationController {

	@EJB
	private RelationManagerBeanLocal managerBeanLocal;

	@ManagedProperty(value = "#{relationVM}")
	private RelationVM relationVM;

	private Set<City> cityList = new HashSet<City>();

	public RelationVM getRelationVM() {
		return relationVM;
	}

	public void setRelationVM(RelationVM relationVM) {
		this.relationVM = relationVM;
	}

	public void findCountry() {// แสดง SQL ให้ดู
		Country country = managerBeanLocal.findCountryById(relationVM.getCountryId());
		relationVM.setCountryName(country.getCountryName());
		Set<City> cities = country.getCities();
		if (cities != null && !cities.isEmpty()) {
			setCityList(cities);
		}
	}

	public void findCity() {
		City city = managerBeanLocal.findCityById(relationVM.getCityId());
		if (city != null) {
			relationVM.setCityName(city.getCityName());
			Country country = city.getCountry();
			if (country != null) {
				relationVM.setCountryName(country.getCountryName());
			}
		}
	}

	public Set<City> getCityList() {
		return cityList;
	}

	public void setCityList(Set<City> cityList) {
		this.cityList = cityList;
	}

}
