package workshop;

import java.sql.Date;

public class transaction {

	private Integer id;
	private String accat_no;
	private Character type;
	private int amount;
	private Date date;
	public transaction(Integer id, String accat_no, Character type, int amount, Date date) {
		super();
		this.id = id;
		this.accat_no = accat_no;
		this.type = type;
		this.amount = amount;
		this.date = date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccat_no() {
		return accat_no;
	}
	public void setAccat_no(String accat_no) {
		this.accat_no = accat_no;
	}
	public Character getType() {
		return type;
	}
	public void setType(Character type) {
		this.type = type;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
