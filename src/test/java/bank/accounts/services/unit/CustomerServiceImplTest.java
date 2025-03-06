package bank.accounts.services.unit;


import bank.accounts.entities.Customer;
import bank.accounts.repository.CustomerRepository;
import bank.accounts.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    public static final long CUSTOMER_ID = 1L;
    public static final long NON_EXISTING_CUSTOMER_ID = 999L;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Test
    void testFindCustomerById_whenCustomerExists() {

        /**
         * Preparar
         */
        Customer customer = getCustomer(CUSTOMER_ID, "Daniel Rúa");
        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(customer));

        /**
         * Actuar y Asegurar
         */
        Assertions.assertDoesNotThrow(() ->{
            Optional<Customer> result = customerServiceImpl.findCustomerById(CUSTOMER_ID);
            assertTrue(result.isPresent(), "El cliente debe estar presente");
            assertEquals(CUSTOMER_ID, result.get().getCustomerId(), "El identificador del cliente debe coincidir");
            assertEquals("Daniel Rúa", result.get().getName(), "El nombre del cliente debe coincidir");
        });

        /**
         * Verificar
         */
        verify(customerRepository, times(1)).findById(CUSTOMER_ID);
    }

    @Test
    void testFindCustomerById_whenCustomerDoesNotExists(){

        /**
         * Preparar
         */
        when(customerRepository.findById(NON_EXISTING_CUSTOMER_ID)).thenReturn(Optional.empty());

        /**
         * Actuar y Asegurar
         */
        Assertions.assertDoesNotThrow(() ->{
            Optional<Customer> result = customerServiceImpl.findCustomerById(NON_EXISTING_CUSTOMER_ID);
            assertFalse(result.isPresent(), "El cliente no debe estar presente");
        });

        /**
         * Verificar
         */
        verify(customerRepository, times(1)).findById(NON_EXISTING_CUSTOMER_ID);
    }

    private static Customer getCustomer(long id, String name){
        return Customer.builder()
                .customerId(id)
                .name(name)
                .build();
    }
}
