package si.unm.fis.prspr.ecom.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import si.unm.fis.prspr.ecom.entity.Items;
import si.unm.fis.prspr.ecom.service.ItemsService;

@RestController
@RequestMapping("/items")
public class ItemsController {
	
	 private static final String UPLOAD_DIR = "C:/Users/Admin/Desktop/crud-project/frontend/Public/test-images/";
	
	ItemsService itemsService;
	
	public ItemsController(ItemsService itemsService) {
		this.itemsService = itemsService;
	}
	
	//Create Operation
	@PostMapping("/createItem")
	public String saveItem(@ModelAttribute Items item, @RequestParam("file") MultipartFile image,
	        HttpSession session) throws IOException {

	    try {
	        String imageName = image.isEmpty() ? "default.jpg" : image.getOriginalFilename();

	        item.setImage(imageName);
	        Items savedItem = itemsService.createItem(item);

	        if (savedItem != null) {
	            File uploadDir = new File(UPLOAD_DIR);
	            if (!uploadDir.exists()) {
	                uploadDir.mkdirs();
	            }

	            Path path = Paths.get(uploadDir.getAbsolutePath(), image.getOriginalFilename());
	            Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

	            session.setAttribute("succMsg", "Product Saved Successfully");
	        } else {
	            session.setAttribute("errorMsg", "Something went wrong on server");
	        }

	        return "Successfully Added";
	    } catch (IOException e) {
	        session.setAttribute("errorMsg", "Failed to save item: " + e.getMessage());
	        return "Error in saving item";
	    }
	}
	
	//Read Operation
	@GetMapping("/getItems")
	public List<Items> getAllItems() {
		return itemsService.getAllItems();
	}
	
	//Read Item by Id
	 @GetMapping("/getItem/{id}")
	    public ResponseEntity<Items> getItemById(@PathVariable int id) {
	        Optional<Items> item = itemsService.getItemById(id);
	        return item.map(ResponseEntity::ok)
	                   .orElseGet(() -> ResponseEntity.notFound().build());
	    }
	
	//Update Operation
	@PutMapping("/updateItem")
	public String updateItem(@RequestBody Items item) {
		return itemsService.updateItem(item);
	}
	
	// PUT end-point to update an Item by ID
    @PutMapping("/updateItem/{id}")
    public ResponseEntity<String> updateSeller(@PathVariable Long id, @RequestBody Items item) {
        // Call service method to update seller
        String result = itemsService.updateItem(item);
        return ResponseEntity.ok(result);
    }
	
	//Delete Operation
	@DeleteMapping("/deleteItem/{id}")
	public String deleteItem(@PathVariable int id) {
		return itemsService.deleteItem(id);
	}

}
