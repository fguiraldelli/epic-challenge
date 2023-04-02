package fguiraldelli.epicchallenge.service;

import fguiraldelli.epicchallenge.dto.CustomerDto;
import fguiraldelli.epicchallenge.entity.Customer;
import fguiraldelli.epicchallenge.exception.CustomerAlreadyExistsException;
import fguiraldelli.epicchallenge.exception.ResourceNotFoundException;
import fguiraldelli.epicchallenge.mapper.CustomerMapper;
import fguiraldelli.epicchallenge.repository.CustomerRepository;
import fguiraldelli.epicchallenge.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTests {

    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer;

    private CustomerDto customerDto;

    @BeforeEach
    public void setup() {

        customer = Customer.builder()
                .customerIdOwner(1L)
                .idCard("ab256xd")
                .name("Albert Einstein")
                .surname("Einstein")
                .address("Berkeley University")
                .build();

        customerDto = CustomerMapper.mapsToCustomerDto(customer);

    }

    @DisplayName("JUnit test for register a customer")
    @Test
    public void registerCustomerTest() {

        //given
        given(customerRepository.findByIdCard(customer.getIdCard()))
                .willReturn(Optional.empty());

        given(customerRepository.save(customer)).willReturn(customer);

        //when
        CustomerDto savedCustomer = customerService.registerCustomer(customerDto);

        //then
        assertThat(savedCustomer).isNotNull();


    }

    @DisplayName("JUnit test for register a customer which throws exception")
    @Test
    public void registerCustomerThatAlreadyExistsExceptionTest() {

        //given
        given(customerRepository.findByIdCard(customer.getIdCard()))
                .willReturn(Optional.of(customer));

        //when
        Assertions.assertThrows(CustomerAlreadyExistsException.class,
                () -> customerService.registerCustomer(customerDto));

        //then
        verify(customerRepository, never()).save(any(Customer.class));


    }

    @DisplayName("JUnit test for delete a customer")
    @Test
    public void deleteCustomerTest() {

        //given
        given(customerRepository.findByIdCard(customer.getIdCard()))
                .willReturn(Optional.of(customer));

        willDoNothing().given(customerRepository).deleteByIdCard(customer.getIdCard());

        //when
        customerService.deleteCustomer(customer.getIdCard());

        //then
        verify(customerRepository, times(1)).deleteByIdCard(customer.getIdCard());

    }

    @DisplayName("JUnit test for register a customer which throws exception")
    @Test
    public void deleteCustomerThatAlreadyExistsExceptionTest() {

        //given
        given(customerRepository.findByIdCard(customer.getIdCard()))
                .willReturn(Optional.empty());

        //when
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> customerService.deleteCustomer(customer.getIdCard()));

        //then
        verify(customerRepository, never()).deleteByIdCard(anyString());


    }
}
