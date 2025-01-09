package bank.accounts.services.integration;


import bank.accounts.entities.Customer;
import bank.accounts.repository.CustomerRepository;
import bank.accounts.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class CustomerServiceImplIntegrationTest {

    public static final long CUSTOMER_ID = 1L;
    public static final long NON_EXISTING_CUSTOMER_ID = 999L;

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setup(){
        Customer customer = Customer.builder()
                .customerId(CUSTOMER_ID)
                .name("Daniel Rúa")
                .lastName("Montoya")
                .email("daniel@example.com")
                .build();
        customerRepository.save(customer);
    }

    @Test
    void testFindCustomerById_whenCustomerExists(){
        /**
         * Como no estamos mokeando, no necesitamos hacer "Preparar" porque estamos trabajndo con datos reales
         * en la funcion setup lo creamos.
         */

        /**
         * No se necesita mokear el repositorio.
         */

        /**
         * Actuar y Asegurar
         */
        Assertions.assertDoesNotThrow(() ->{
            Optional<Customer> result = customerServiceImpl.findCustomerById(CUSTOMER_ID);
            assertTrue(result.isPresent(), "El cliente debe de estar presente");
            assertEquals(CUSTOMER_ID, result.get().getCustomerId(), "El identificador del cliente debe coincidir");
            assertEquals("Daniel Rúa", result.get().getName(), "El nombre del cliente debe coincidir");
        });
    }

    @Test
    void testFindCustomerById_whenCustomerDoesNotExist(){
        /**
         * Como no estamos mokeando, no necesitamos hacer "Preparar" porque estamos trabajndo con datos reales
         * en la funcion setup lo creamos.
         */

        /**
         * No se necesita mokear el repositorio.
         */

        /**
         * Actuar y Asegurar
         */
        Assertions.assertDoesNotThrow(() ->{
            Optional<Customer> result = customerServiceImpl.findCustomerById(NON_EXISTING_CUSTOMER_ID);
            assertFalse(result.isPresent());
        });
    }

    @AfterEach
    public void tearDown(){
        customerRepository.deleteAll();
    }
}
