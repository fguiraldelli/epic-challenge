package fguiraldelli.epicchallenge.service.impl;

import fguiraldelli.epicchallenge.dto.CustomerDto;
import fguiraldelli.epicchallenge.entity.Customer;
import fguiraldelli.epicchallenge.exception.CustomerAlreadyExistsException;
import fguiraldelli.epicchallenge.exception.ResourceNotFoundException;
import fguiraldelli.epicchallenge.mapper.CustomerMapper;
import fguiraldelli.epicchallenge.repository.CustomerRepository;
import fguiraldelli.epicchallenge.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    @Override
    public CustomerDto registerCustomer(CustomerDto customer) {

        Optional<Customer> optionalCustomer = customerRepository.findByIdCard(customer.getIdCard());

        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already exists in database");
        }

        Customer newCustomer = CustomerMapper.mapsToCustomer(customer);

        Customer savedCustomer = customerRepository.save(newCustomer);

        return CustomerMapper.mapsToCustomerDto(savedCustomer);

    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customer) {
        Customer existingCustomer= customerRepository.findByIdCard(customer.getIdCard()).orElseThrow(
                () -> new ResourceNotFoundException("Customer","id card", customer.getIdCard())
        );

        existingCustomer.setIdCard(customer.getIdCard());
        existingCustomer.setName(customer.getName());
        existingCustomer.setSurname(customer.getSurname());
        existingCustomer.setAddress(customer.getAddress());

        Customer updatedCustomer = customerRepository.save(existingCustomer);

        return CustomerMapper.mapsToCustomerDto(updatedCustomer);
    }

    @Override
    public void deleteCustomer(String idCard) {
        Customer existingCustomer = customerRepository.findByIdCard(idCard).orElseThrow(
                () -> new ResourceNotFoundException("Customer","id card", idCard)
        );

        customerRepository.deleteByIdCard(idCard);
    }
}
