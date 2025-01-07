package bank.accounts.controller;

import bank.accounts.dto.request.CustomerCreateRequestDTO;
import bank.accounts.dto.response.CustomerCreateResponseDTO;
import bank.accounts.entities.Customer;
import bank.accounts.services.CustomerService;
import bank.accounts.services.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCusomterById(@PathVariable Long id) {
        return null;
    }

    @PostMapping("/customer/create")
    public Customer createCustomer(@RequestBody Customer request) throws Exception{
        return customerService.createNewCustomer(request);
    }
}
