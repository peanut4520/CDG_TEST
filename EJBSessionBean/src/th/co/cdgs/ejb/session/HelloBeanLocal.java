package th.co.cdgs.ejb.session;

import javax.ejb.Local;

@Local
public interface HelloBeanLocal {

	public String hello(String message);

}
