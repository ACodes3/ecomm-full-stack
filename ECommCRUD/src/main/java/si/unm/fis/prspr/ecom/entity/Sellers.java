package si.unm.fis.prspr.ecom.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Sellers {
	@Id
	private int id;
	private String name;
	private String company;
	private String phone;
	private String email;
	
	@OneToMany(mappedBy ="seller")
	@JsonIgnore
	private List<Items> itemsList;
	
	@ManyToOne
	private Regions region;

	public Sellers() {
		super();
	}

	public Sellers(int id, String name, String company, String phone, String email, List<Items> itemsList,
			Regions region) {
		super();
		this.id = id;
		this.name = name;
		this.company = company;
		this.phone = phone;
		this.email = email;
		this.itemsList = itemsList;
		this.region = region;
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Items> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<Items> itemsList) {
		this.itemsList = itemsList;
	}

	public Regions getRegion() {
		return region;
	}

	public void setRegion(Regions region) {
		this.region = region;
	}
	

}
