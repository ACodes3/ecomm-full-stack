package si.unm.fis.prspr.ecom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import si.unm.fis.prspr.ecom.entity.Regions;
import si.unm.fis.prspr.ecom.entity.Sellers;
import si.unm.fis.prspr.ecom.repository.RegionsRepository;
import si.unm.fis.prspr.ecom.repository.SellersRepository;

@Service
public class SellersService {
	SellersRepository sellersRepository;
	RegionsRepository regionsRepository;
	public SellersService(SellersRepository sellersRepository, RegionsRepository regionsRepository) {
		this.sellersRepository = sellersRepository;
		this.regionsRepository = regionsRepository;
	}
	
	
	//Create
	public String createSeller(Sellers seller) {
		sellersRepository.save(seller);
		return "Seller added successfully";
	}
	
	//Read
	public List<Sellers> getAllSellers(){
		return sellersRepository.findAll();
	}
	
	//Update
	public String updateSeller(Sellers seller) {
		if(sellersRepository.findById(seller.getId()).isPresent()) {
			sellersRepository.save(seller);
			return " Seller is successfully updated.";
		} else {
			return "Seller does not exist!";
		}
	}
	
	//Delete
	public String deleteSeller(int id) {
		if(sellersRepository.findById(id).isPresent()) {
			sellersRepository.deleteById(id);
			return " Seller is successfully deleted.";
		} else {
			return "Seller does not exist!";
		}
	}
	
	//Find Seller by Region ID
	public List<Sellers> getSellersByRegion(int regionId) {
        Optional<Regions> optRegion = regionsRepository.findById(regionId);
        if (optRegion.isPresent()) {
        	return optRegion.get().getItemsList();
        } else {
            return new ArrayList<Sellers>();
        }
	}

}
