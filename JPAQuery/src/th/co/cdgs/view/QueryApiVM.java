package th.co.cdgs.view;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class QueryApiVM {
	private String countryName;
	private String cityName;

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
