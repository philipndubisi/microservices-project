package com.philcode.accounts.mapper;

import com.philcode.accounts.dto.CustomerDto;
import com.philcode.accounts.entity.Customer;

public class CustomerMapper {
    public static CustomerDto maptToCustomerDTO(Customer customer, CustomerDto customerDTO){
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }
    public static Customer mapToCustomer(CustomerDto customerDTO, Customer customer){
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        return customer;
    }
}
