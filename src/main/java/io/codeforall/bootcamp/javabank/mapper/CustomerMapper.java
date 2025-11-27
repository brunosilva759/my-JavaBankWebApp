package io.codeforall.bootcamp.javabank.mapper;

import io.codeforall.bootcamp.javabank.persistence.dto.CustomerDto;
import io.codeforall.bootcamp.javabank.persistence.model.Customer;

public class CustomerMapper {

    Customer customer;
    CustomerDto customerDto;

    public CustomerDto toDTO(Customer customer){
        customerDto = new CustomerDto();

        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setEmail(customerDto.getEmail());
        customerDto.setPhone(customer.getPhone());

        return customerDto;
    }


    public Customer toEntity(CustomerDto customerDto){
        customer = new Customer();

        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPhone(customerDto.getPhone());
        customer.setEmail(customerDto.getEmail());

        return customer;
    }


}
