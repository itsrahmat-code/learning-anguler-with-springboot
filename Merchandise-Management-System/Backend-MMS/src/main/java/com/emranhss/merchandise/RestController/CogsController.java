package com.emranhss.merchandise.RestController;

import com.emranhss.merchandise.entity.Cogs;
import com.emranhss.merchandise.service.CogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cogs")
@CrossOrigin("*")
public class CogsController {

        private final CogsService cogsService;

        @Autowired
        public CogsController(CogsService cogsService) {
            this.cogsService = cogsService;
        }

        // 🔸 Create new COGS record
        @PostMapping("/add")
        public ResponseEntity<?> createCogs(@RequestBody Cogs cogs) {
            if (cogs.getProductName() == null || cogs.getProductQty() == null || cogs.getProductQty() <= 0) {
                return ResponseEntity.badRequest().body("Product name and quantity must be provided and quantity > 0.");
            }

            Cogs saved = cogsService.createCogs(cogs);
            return ResponseEntity.ok(saved);
        }

        // 🔸 Get one COGS by ID
        @GetMapping("/{id}")
        public ResponseEntity<?> getCogsById(@PathVariable Long id) {
            Cogs cogs = cogsService.getCogsById(id);
            if (cogs == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(cogs);
        }

        // 🔸 Get all COGS records
        @GetMapping("")
        public List<Cogs> getAllCogs() {
            return cogsService.getAllCogs();
        }

        // 🔸 Update a COGS record
        @PutMapping("/{id}")
        public ResponseEntity<?> updateCogs(@PathVariable Long id, @RequestBody Cogs cogs) {
            Cogs updated = cogsService.updateCogs(id, cogs);
            if (updated == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updated);
        }

        // 🔸 Delete a COGS record
        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteCogs(@PathVariable Long id) {
            Cogs existing = cogsService.getCogsById(id);
            if (existing == null) {
                return ResponseEntity.notFound().build();
            }

            cogsService.deleteCogs(id);
            return ResponseEntity.ok("Deleted successfully.");
        }
    }
