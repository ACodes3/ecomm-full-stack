package si.unm.fis.prspr.ecom.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Regions {
	
	@Id
	private int id;
	private String name;
	
	@OneToMany(mappedBy ="region")
	@JsonIgnore
	private List<Sellers> itemsList;

	public Regions() {
		super();
	}

	public Regions(int id, String name, List<Sellers> itemsList) {
		super();
		this.id = id;
		this.name = name;
		this.itemsList = itemsList;
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

	public List<Sellers> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<Sellers> itemsList) {
		this.itemsList = itemsList;
	}

}

