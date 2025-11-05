package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.services.AuthServiceImpl;
import io.codeforall.bootcamp.javabank.services.CustomerService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthServiceImplTest {

    private AuthServiceImpl authService;
    private CustomerService customerService;
    private Customer customer;

    @Before
    public void setup() {

        authService = new AuthServiceImpl();
        customerService = mock(CustomerService.class);
        customer = mock(Customer.class);
    }

    @Test
    public void authenticate() {

        when(customerService.get(1)).thenReturn(customer);


        authService.setCustomerService(customerService);

        boolean autheticated = authService.authenticate(1);

        assertTrue(autheticated);
        assertEquals(customer, authService.getAccessingCustomer());

    }
}
