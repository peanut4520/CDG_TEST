package th.co.cdgs.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import th.co.cdgs.ejb.session.bean.QueryAPIManager;
import th.co.cdgs.jpa.entity.City;
import th.co.cdgs.jpa.entity.Country;

@ManagedBean
public class QueryApiController {

	@EJB
	private QueryAPIManager queryAPIManager;

	@ManagedProperty(value = "#{queryApiVM}")
	QueryApiVM queryApiVM;

	public QueryApiVM getQueryApiVM() {
		return queryApiVM;
	}

	public void setQueryApiVM(QueryApiVM queryApiVM) {
		this.queryApiVM = queryApiVM;
	}

	private List<Country> countryList = new ArrayList<>();
	private List<City> cityList = new ArrayList<>();

	public void queryCountry() {
		countryList = queryAPIManager.queryCountry();
	}

	public void queryCountryByName() {
		countryList = queryAPIManager.queryCountryByName(queryApiVM.getCountryName());
	}

	public void testJPQL() {
		queryAPIManager.queryJPQL();
	}

	public void queryJoin() {
		queryAPIManager.queryJoin();
	}

	public void queryCountryJoinFetch() {
		List<Country> list = queryAPIManager.queryCountryJoinFecth();

		for (Country country : list) {
			System.out.println(" Country : " + country.getCountryName());

			Set<City> cities = country.getCities();
			Iterator<City> iterator = cities.iterator();
			while (iterator.hasNext()) {
				City city = (City) iterator.next();
				System.out.println(" City : " + city.getCityName());
			}
			System.out.println(" ==================   ");
		}
	}

	public void queryCityJoinFetch() {
		List<City> list = queryAPIManager.queryCityJoinFecth();
		int count = 1;
		for (City city : list) {
			System.out.println("======    " + count++ + "  " + city.getCityName() + " is in "
					+ city.getCountry().getCountryName());
		}

	}

	public void queryNativeSQL() {
		City city = queryAPIManager.queryNativeSQL(queryApiVM.getCityName());
		cityList.add(city);
	}

	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

}
