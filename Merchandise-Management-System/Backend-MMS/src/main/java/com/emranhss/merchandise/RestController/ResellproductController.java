package com.emranhss.merchandise.RestController;
import com.emranhss.merchandise.entity.ReSellProduct;
import com.emranhss.merchandise.service.ReSellProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resellproduct")
@CrossOrigin("*")
public class ResellproductController {

    @Autowired
    private ReSellProductService reSellProductService;

    @PostMapping("/add")
    public ReSellProduct createReSellProduct(@RequestBody ReSellProduct reSellProduct) {
        return reSellProductService.save(reSellProduct);
    }

    @GetMapping
    public List<ReSellProduct> getAllReSellProducts() {
        return reSellProductService.getAllReSellProducts();
    }

    @GetMapping("/{id}")
    public ReSellProduct getReSellProductById(@PathVariable Long id) {
        return reSellProductService.getById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public ReSellProduct updateReSellProduct(@PathVariable Long id, @RequestBody ReSellProduct updatedReSellProduct) {
        return reSellProductService.update(id, updatedReSellProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteReSellProduct(@PathVariable Long id) {
        reSellProductService.delete(id);
    }



}
