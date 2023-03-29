package fguiraldelli.epicchallenge.service;

import fguiraldelli.epicchallenge.dto.CustomerDto;

public interface CustomerService {
    CustomerDto registerCustomer(CustomerDto customer);
    CustomerDto updateCustomer(CustomerDto customer);
    void deleteCustomer(String idCard);
}
