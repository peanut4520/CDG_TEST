package workshop;

public class Book {
	private Integer id;
	private String title;
	private String author;
	private Integer publication_year ;
	private int unit_price;
	
	
	public Book(Integer id, String title, String author, Integer publication_year, int unit_price) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.publication_year = publication_year;
		this.unit_price = unit_price;
	}


	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publication_year=" + publication_year
				+ ", unit_price=" + unit_price + "]";
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
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


	public Integer getPublication_year() {
		return publication_year;
	}


	public void setPublication_year(Integer publication_year) {
		this.publication_year = publication_year;
	}


	public int getUnit_price() {
		return unit_price;
	}


	public void setUnit_price(int unit_price) {
		this.unit_price = unit_price;
	}


	public static void getConnection() {
		
	}

	

}
