package th.co.cdgs.view;

import java.util.Properties;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import th.co.cdgs.ejb.session.HelloBeanLocal;
import th.co.cdgs.ejb.session.HelloBeanRemote;

@ManagedBean
public class HelloWorldController {
	@EJB
	HelloBeanLocal hellobeanLocal;
	private String message;

	public void helloEJB() {
		message = hellobeanLocal.hello("Tong"); /// return String >> USE varable "messge"

	}

	public void lookupJNDI() {
		try {
			Context context = new InitialContext();
			Object ejbHome = context.lookup("java:app/EJBSessionBean/HelloBean!th.co.cdgs.ejb.session.HelloBeanLocal");
			HelloBeanLocal local = (HelloBeanLocal) ejbHome;
			message = local.hello("Hello I am Tong");

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loopupRemote() {
		try {
			Properties jndiProps = new Properties();
			// jndiProps.put(Context.INITIAL_CONTEXT_FACTORY,
			// "org.jboss.naming.remote.client.InitialContextFactory");
			jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");

			jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			jndiProps.put("jboss.naming.client.ejb.context", true);
			jndiProps.put(Context.PROVIDER_URL, "http-remoting://10.17.1.164:8080");
			jndiProps.put(Context.SECURITY_PRINCIPAL, "tester");
			jndiProps.put(Context.SECURITY_CREDENTIALS, "tester");
			
			// create a context passing these properties
			Context context = new InitialContext(jndiProps);
			Object ejbHome = context.lookup("/EJBSessionBean/HelloBean!th.co.cdgs.ejb.session.HelloBeanRemote");
			HelloBeanRemote remote = (HelloBeanRemote) ejbHome;
			this.message = remote.helloRemote("EJB Remote call ");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
