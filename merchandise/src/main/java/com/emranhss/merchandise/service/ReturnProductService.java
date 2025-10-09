package com.emranhss.merchandise.service;

import com.emranhss.merchandise.entity.ReSellProduct;
import com.emranhss.merchandise.entity.ReturnProduct;
import com.emranhss.merchandise.repository.ReturnProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@Service
public class ReturnProductService {

    @Autowired
    private ReturnProductRepo returnProductRepo;
    @Autowired
    private ReSellProductService resellStockService; // ✅ Add this
    @Value("${image.upload.dir}")
    private String uploadDir;

    /**
     * Get all returned or damaged products
     */
    public List<ReturnProduct> getAllReturnProducts() {
        return returnProductRepo.findAll();
    }

    /**
     * Get a return product by its ID
     */
    public ReturnProduct getReturnProductById(Long id) {
        return returnProductRepo.findById(id).orElse(null);
    }

    /**
     * Create a new return product
     */
    public ReturnProduct createReturnProduct(ReturnProduct returnProduct, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            String fileName = saveImage(file, returnProduct.getProductName());
            returnProduct.setPhoto(fileName);
        }
        return returnProductRepo.save(returnProduct);
    }

    /**
     * Update an existing return product
     */
    public ReturnProduct updateReturnProduct(Long id, ReturnProduct updatedProduct) {
        return returnProductRepo.findById(id).map(existing -> {
            existing.setInvoice(updatedProduct.getInvoice());
            existing.setType(updatedProduct.getType());
            existing.setQuantity(updatedProduct.getQuantity());
            existing.setDate(updatedProduct.getDate());
            existing.setReason(updatedProduct.getReason());
            existing.setProductName(updatedProduct.getProductName());
            existing.setPhoto(updatedProduct.getPhoto()); // only if you're allowing photo update here
            return returnProductRepo.save(existing);
        }).orElse(null);
    }


    /**
     * Delete a return product by ID
     */
    public void deleteReturnProduct(Long id) {
        returnProductRepo.deleteById(id);
    }

    /**
     * Save image and return filename
     */
    public String saveImage(MultipartFile file, String productName) {
        Path uploadPath = Paths.get(uploadDir, "return_product");
        try {
            Files.createDirectories(uploadPath);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory: " + uploadPath, e);
        }

        String originalFilename = file.getOriginalFilename();
        String extension = "";

        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf('.')).toLowerCase();
        }

        String safeProductName = productName.trim().replaceAll("[^a-zA-Z0-9_-]", "_");
        String fileName = safeProductName + "_" + UUID.randomUUID() + extension;

        try {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Could not save image file: " + fileName, e);
        }

        return fileName;
    }


    public ReturnProduct markAsFixedAndResell(Long id) throws Exception {
        ReturnProduct rp = returnProductRepo.findById(id).orElseThrow(() -> new Exception("ReturnProduct not found"));
        rp.setStatus("FIXED");
        returnProductRepo.save(rp);

        // Now create ResellStock record
        ReSellProduct rs = new ReSellProduct();
        rs.setName(rp.getProductName());
        rs.setQty(rp.getQuantity());// assuming ReturnProduct has a field "price" or cost to set price
        rs.setPrice(rp.getPrice());
        rs.setDetails("From return product id: " + rp.getId());

        resellStockService.save(rs);

        return rp;
    }
}
