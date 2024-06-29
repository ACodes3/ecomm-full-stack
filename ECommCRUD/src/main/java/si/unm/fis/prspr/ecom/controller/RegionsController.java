package si.unm.fis.prspr.ecom.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import si.unm.fis.prspr.ecom.entity.Regions;
import si.unm.fis.prspr.ecom.service.RegionsService;

@RestController
@RequestMapping("/regions")
public class RegionsController {
	RegionsService regionsService;
	public RegionsController(RegionsService regionsService) {
		this.regionsService = regionsService;
	}
	
	//Create Operation
	@PostMapping("/createRegion")
	public String createRegion(@RequestBody Regions region) {
		return regionsService.createRegion(region);
	}
	
	//Read Operation
	@GetMapping("/getRegions")
	public List<Regions> getAllRegions() {
		return regionsService.getAllRegions();
	}
	
	//Update Operation
	@PutMapping("/updateRegion")
	public String updateRegion(@RequestBody Regions region) {
		return regionsService.updateRegion(region);
	}
	
	//Delete Operation
	@DeleteMapping("/deleteRegion/{id}")
	public String deleteRegion(@PathVariable int id) {
		return regionsService.deleteRegion(id);
	}

}
