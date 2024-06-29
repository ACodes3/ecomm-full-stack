package si.unm.fis.prspr.ecom.entity;

import java.math.BigInteger;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Items {
	@Id
	private int id;
	private String name;
	private BigInteger price;
	private String brand;
	private String image;
	private LocalDate created_at;
	
	@ManyToOne
	private Sellers seller;
	
	public Items() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getPrice() {
		return price;
	}

	public void setPrice(BigInteger price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public LocalDate getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDate created_at) {
		this.created_at = created_at;
	}

	public Sellers getSeller() {
		return seller;
	}

	public void setSeller(Sellers seller) {
		this.seller = seller;
	}

	public Items(int id, String name, BigInteger price, String brand, String image, LocalDate created_at,
			Sellers seller) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.brand = brand;
		this.image = image;
		this.created_at = created_at;
		this.seller = seller;
	}
	

}
