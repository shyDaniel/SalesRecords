package app.controller;

import app.entity.Sale;
import app.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    // Method to update an existing sale
    @PutMapping("/sales/{id}")
    public ResponseEntity<Sale> updateSale(@PathVariable(value = "id") int id, @RequestBody Sale saleDetails) {
        Optional<Sale> saleOptional = salesRepository.findById(id);
        if (saleOptional.isPresent()) {
            Sale sale = saleOptional.get();
            sale.setTitle(saleDetails.getTitle());
            sale.setAmount(saleDetails.getAmount());
            sale.setCustomer(saleDetails.getCustomer());
            sale.setDate(saleDetails.getDate());
            final Sale updatedSale = salesRepository.save(sale);
            return ResponseEntity.ok(updatedSale);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Method to delete a sale
    @DeleteMapping("/sales/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable(value = "id") int id) {
        Optional<Sale> saleOptional = salesRepository.findById(id);
        if (saleOptional.isPresent()) {
            salesRepository.delete(saleOptional.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}