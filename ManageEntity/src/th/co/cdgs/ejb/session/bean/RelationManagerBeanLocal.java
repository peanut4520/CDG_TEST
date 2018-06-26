package th.co.cdgs.ejb.session.bean;

import javax.ejb.Local;

import th.co.cdgs.jpa.entity.City;
import th.co.cdgs.jpa.entity.Country;

@Local
public interface RelationManagerBeanLocal {
	public Country findCountryById(Long countryId);
	public City findCityById(Long cityId);
}
