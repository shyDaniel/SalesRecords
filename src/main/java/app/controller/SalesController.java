package app.controller;


import app.entity.Sale;
import app.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SalesController {

    @Autowired
    private SalesRepository salesRepository;

    @GetMapping("/header")
    public String getHeader() {
        return "Your Sales Dashboard:";
    }

    @GetMapping("/content")
    public String getContent() {
        return "Log a new Sale and checkout your sales records";
    }

    // Method to save a new sale
    @PostMapping("/sales")
    public Sale createSale(@RequestBody Sale sale) {
        return salesRepository.save(sale);
    }

    // Method to retrieve all sales
    @GetMapping("/sales")
    public List<Sale> getAllSales() {
        return salesRepository.findAll();
    }
}


