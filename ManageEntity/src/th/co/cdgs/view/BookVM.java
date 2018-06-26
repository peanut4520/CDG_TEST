package th.co.cdgs.view;

import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class BookVM {
	
	private Long id;
	private String title;
	private String author;
	private Long publicationYear;
	private BigDecimal unitPrice;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Long getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(Long publicationYear) {
		this.publicationYear = publicationYear;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	
	
}
