package bank.accounts.controller;

import bank.accounts.entities.Customer;
import bank.accounts.services.CustomerService;
import jakarta.persistence.GeneratedValue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customer/{id}")
    public Optional<Customer> getCustomerById(@PathVariable long id){
        return customerService.findCustomerById(id);
    }

    @PostMapping("/customer/create")
    public Customer createCustomer(@RequestBody Customer request) {
        return customerService.createNewCustomer(request);
    }

    @DeleteMapping("/customer/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable long id) {
       return customerService.deleteCustomerById(id);
    }

    @PutMapping("/customer/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable long id, @RequestBody Customer customer){
        return customerService.updateCustomer(id, customer);
    }
}