package com.emranhss.merchandise.RestController;

import com.emranhss.merchandise.entity.ReturnProduct;
import com.emranhss.merchandise.service.ReturnProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/returnproduct")
@CrossOrigin("*")
public class ReturnProductController {

    private final ReturnProductService returnProductService;

    @Autowired
    public ReturnProductController(ReturnProductService returnProductService) {
        this.returnProductService = returnProductService;
    }

    /**
     * Create a new return or damage product with image
     */
    @PostMapping("")
    public ResponseEntity<Map<String, String>> saveReturnProduct(
            @RequestPart("returnProduct") String returnProductJson,
            @RequestParam(value = "photo", required = false) MultipartFile photo
    ) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ReturnProduct returnProduct = objectMapper.readValue(returnProductJson, ReturnProduct.class);

            returnProductService.createReturnProduct(returnProduct, photo);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Return or Damage Product added successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(Map.of("message", "Invalid JSON: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get all return/damage products
     */
    @GetMapping("")
    public ResponseEntity<List<ReturnProduct>> getAllReturnProducts() {
        return new ResponseEntity<>(returnProductService.getAllReturnProducts(), HttpStatus.OK);
    }

    /**
     * Get a single return/damage product by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ReturnProduct> getReturnProductById(@PathVariable Long id) {
        ReturnProduct returnProduct = returnProductService.getReturnProductById(id);
        return returnProduct != null
                ? new ResponseEntity<>(returnProduct, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Update a return/damage product
     */
    @PutMapping("/{id}")
    public ResponseEntity<ReturnProduct> updateReturnProduct(
            @PathVariable Long id,
            @RequestBody ReturnProduct returnProductDetails
    ) {
        ReturnProduct updated = returnProductService.updateReturnProduct(id, returnProductDetails);
        return updated != null
                ? new ResponseEntity<>(updated, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete a return/damage product
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteReturnProduct(@PathVariable Long id) {
        try {
            returnProductService.deleteReturnProduct(id);
            return new ResponseEntity<>(Map.of("message", "Return or Damage Product deleted successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Failed to delete: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





    @PostMapping("/markFixed/{id}")
    public ResponseEntity<?> markFixed(@PathVariable Long id) {
        try {
            ReturnProduct rp = returnProductService.markAsFixedAndResell(id);
            return ResponseEntity.ok(rp);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error marking as fixed: " + e.getMessage());
        }
    }
}
