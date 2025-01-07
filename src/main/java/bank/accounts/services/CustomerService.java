package bank.accounts.services;

import bank.accounts.dto.request.CustomerCreateRequestDTO;
import bank.accounts.dto.response.CustomerCreateResponseDTO;
import bank.accounts.entities.Customer;

import java.util.Optional;

public interface CustomerService {
    Customer createNewCustomer(Customer customer);
    Optional<Customer> findCustomerById(Long id);
}
