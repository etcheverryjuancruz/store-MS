package com.jetcheverry.CustomerService.service;

import com.jetcheverry.CustomerService.entity.Customer;
import com.jetcheverry.CustomerService.entity.Region;
import com.jetcheverry.CustomerService.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class CustomerServiceImpl  implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> findCustomerAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer deleteCustomer(Customer customer) {
       return customerRepository.findById(customer.getId()).map( cust -> {
           cust.setState("DELETED");
           return cust;
       }).orElse(null);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.findById(customer.getId()).map(customerDB -> {
            customerDB.setFirstName(customer.getFirstName());
            customerDB.setLastName(customer.getLastName());
            customerDB.setEmail(customer.getEmail());
            customerDB.setPhotoUrl(customer.getPhotoUrl());
            return  customerRepository.save(customerDB);
        }).orElse(null);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer customerDB = customerRepository.findByNumberID(customer.getNumberID());
        if (customerDB != null) {
            return customerDB;
        }
        customer.setState("CREATED");
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findCustomersByRegion(Region region) {
        return customerRepository.findByRegion(region);
    }
}

