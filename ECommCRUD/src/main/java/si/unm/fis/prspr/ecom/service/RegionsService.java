package si.unm.fis.prspr.ecom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import si.unm.fis.prspr.ecom.entity.Regions;
import si.unm.fis.prspr.ecom.repository.RegionsRepository;

@Service
public class RegionsService {
	RegionsRepository regionsRepository;
	public RegionsService(RegionsRepository regionsRepository) {
		this.regionsRepository = regionsRepository;
	}
	
	//Create
	public String createRegion(Regions region) {
		regionsRepository.save(region);
		return "Region is added successfully";
	}
	
	//Read
	public List<Regions> getAllRegions(){
		return regionsRepository.findAll();
	}
	
	//Update
	public String updateRegion(Regions region) {
		if(regionsRepository.findById(region.getId()).isPresent()) {
			regionsRepository.save(region);
			return " Region is successfully updated.";
		} else {
			return "Region does not exist!";
		}
	}
	
	//Delete
	public String deleteRegion(int id) {
		if(regionsRepository.findById(id).isPresent()) {
			regionsRepository.deleteById(id);
			return " Region is successfully deleted.";
		} else {
			return "Region does not exist!";
		}
	}
	
	//Get Region by Id
	public Regions getRegionById(int id) {
	    Optional<Regions> region = regionsRepository.findById(id);
	    if (region.isPresent()) {
	        return region.get();
	    } else {
	        return null;
	    }
	}
}
