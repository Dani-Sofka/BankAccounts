package bank.accounts.services;

import bank.accounts.entities.Customer;
import bank.accounts.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface CustomerService {
    Optional<Customer> findCustomerById(long id);
    Customer createNewCustomer(Customer customer);
    ResponseEntity<String> deleteCustomerById(long id);
    ResponseEntity<Customer> updateCustomer(long id, Customer customer);
}
