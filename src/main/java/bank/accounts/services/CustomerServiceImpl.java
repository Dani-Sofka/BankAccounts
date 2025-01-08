package bank.accounts.services;

import bank.accounts.entities.Customer;
import bank.accounts.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Optional<Customer> findCustomerById(long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer createNewCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public ResponseEntity<String> deleteCustomerById(long id) {
        if (customerRepository.existsById(id)){
            customerRepository.deleteById(id);
            return ResponseEntity.ok("Cliente eliminado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }
    }

    @Override
    public ResponseEntity<Customer> updateCustomer(long id, Customer customer) {
        Optional<Customer> customerExist = customerRepository.findById(id);
        if (customerExist.isPresent()){
            Customer updateCustomer = Customer.builder()
                    .customerId(id)
                    .name(customer.getName())
                    .lastName(customer.getLastName())
                    .email(customer.getEmail())
                    .build();
            customerRepository.save(updateCustomer);
            return ResponseEntity.ok(updateCustomer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
