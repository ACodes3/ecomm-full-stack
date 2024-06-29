package si.unm.fis.prspr.ecom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import si.unm.fis.prspr.ecom.entity.Items;
import si.unm.fis.prspr.ecom.repository.ItemRepository;

//CRUD Operations
@Service
public class ItemsService {
	ItemRepository itemRepository;
	public ItemsService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	
	//Create
	public Items createItem(Items item) {
	    return itemRepository.save(item);
	}
	
	//Read
	public List<Items> getAllItems() {
		return itemRepository.findAll();
	}
	
	//Update
		public String updateItem(Items item) {
			if(itemRepository.findById(item.getId()).isPresent()) {
				itemRepository.save(item);
				return " Item is successfully updated.";
			} else {
				return "Item does not exist!";
			}
		}
	
	//Delete
	public String deleteItem(int id) {
		if(itemRepository.findById(id).isPresent()) {
			itemRepository.deleteById(id);
			return " Item is successfully deleted.";
		} else {
			return "Item does not exist!";
		}
	}
	
	//Item Display
	// Read item by ID
    public Optional<Items> getItemById(int id) {
        return itemRepository.findById(id);
    }

}
