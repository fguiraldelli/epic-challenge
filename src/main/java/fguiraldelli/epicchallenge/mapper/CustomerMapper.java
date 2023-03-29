package fguiraldelli.epicchallenge.mapper;

import fguiraldelli.epicchallenge.dto.CustomerDto;
import fguiraldelli.epicchallenge.entity.Customer;

public class CustomerMapper {
    public static CustomerDto mapsToCustomerDto(Customer customer){
        CustomerDto customerDto = new CustomerDto(
                customer.getCustomerIdOwner(),
                customer.getIdCard(),
                customer.getName(),
                customer.getSurname(),
                customer.getAddress()
        );
        return customerDto;
    }

    public static Customer mapsToCustomer(CustomerDto customerDto) {
        Customer customer = new Customer(
                customerDto.getId(),
                customerDto.getIdCard(),
                customerDto.getName(),
                customerDto.getSurname(),
                customerDto.getAddress()
        );
        return customer;
    }
}
