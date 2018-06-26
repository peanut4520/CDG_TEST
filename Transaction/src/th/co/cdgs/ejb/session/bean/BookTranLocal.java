package th.co.cdgs.ejb.session.bean;

import javax.ejb.Local;

@Local
public interface BookTranLocal {

	public void testRequire();

	public void testRequireNew();

	public void testSupport();

	public void testNotSupport();
	
	public void testMandatory();
	
	public void testNever();
}
