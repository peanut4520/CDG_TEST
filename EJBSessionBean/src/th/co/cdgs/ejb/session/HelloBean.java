package th.co.cdgs.ejb.session;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class HelloBean
 */

@Stateless
@LocalBean
public class HelloBean implements HelloBeanLocal, HelloBeanRemote {

	@Override
	public String hello(String message) {
		return "Hello World ! " + message;
	}

	@Override
	public String helloRemote(String message) {
		System.out.println("message : "+ message);
		return "Hello world Remote" + message;
	}

}
