package th.co.cdgs.ejb.session.bean;

import java.util.List;

import javax.ejb.Local;

import th.co.cdgs.jpa.entity.Country;

@Local
public interface QueryAPILocal {
	
	public List<Country> queryCountry();
	
	public List<Country> queryCountryByName(String name);
	
	public void queryJPQL();
	
	public void queryJoin();
}
