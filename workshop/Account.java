package workshop;

public class Account {
	private String acct_no;
	private String acct_name;
	private int balance;

	public Account(String acct_no, String acct_name, int balance) {
		super();
		this.acct_no = acct_no;
		this.acct_name = acct_name;
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [acct_no=" + acct_no + ", acct_name=" + acct_name + ", balance=" + balance + "]";
	}

	public String getAcct_no() {
		return acct_no;
	}

	public void setAcct_no(String acct_no) {
		this.acct_no = acct_no;
	}

	public String getAcct_name() {
		return acct_name;
	}

	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Account() {
		// TODO Auto-generated constructor stub
	}

}
