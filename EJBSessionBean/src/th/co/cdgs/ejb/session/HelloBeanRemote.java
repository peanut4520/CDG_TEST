package th.co.cdgs.ejb.session;

import javax.ejb.Remote;

@Remote
public interface HelloBeanRemote {
	public String helloRemote(String message);
}
