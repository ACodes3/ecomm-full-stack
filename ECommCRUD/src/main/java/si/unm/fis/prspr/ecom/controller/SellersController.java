package si.unm.fis.prspr.ecom.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import si.unm.fis.prspr.ecom.entity.Sellers;
import si.unm.fis.prspr.ecom.service.SellersService;

@RestController
@RequestMapping("/sellers")
public class SellersController {
	SellersService sellersService;
	public SellersController(SellersService sellersService) {
		this.sellersService = sellersService;
	}
	
	//Create Operation
	@PostMapping("/createSeller")
	public String createSeller(@RequestBody Sellers seller) {
		return sellersService.createSeller(seller);
	}
	
	//Read Operation
	@GetMapping("/getSellers")
	public List<Sellers> getAllSellers() {
		return sellersService.getAllSellers();
	}
	
	//Update Operation
	@PutMapping("/updateSeller")
	public String updateSeller(@RequestBody Sellers seller) {
		return sellersService.updateSeller(seller);
	}
	
	// PUT endpoint to update a seller by ID
    @PutMapping("/updateSeller/{id}")
    public ResponseEntity<String> updateSeller(@PathVariable Long id, @RequestBody Sellers seller) {
        // Call service method to update seller
        String result = sellersService.updateSeller(seller);
        return ResponseEntity.ok(result);
    }
	
	//Delete Operation
	@DeleteMapping("/deleteSeller/{id}")
	public String deleteSeller(@PathVariable int id) {
		return sellersService.deleteSeller(id);
	}
	
	//Find Sellers by Region ID
	@GetMapping("/getSellerByRegionId/{regionId}")
	public List<Sellers> getSellerByRegionId(@PathVariable int regionId) {
		return sellersService.getSellersByRegion(regionId);
	}

}
