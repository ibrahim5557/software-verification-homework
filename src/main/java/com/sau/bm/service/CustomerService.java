package com.sau.bm.service;

import com.sau.bm.dtos.CustomerDTO;

import java.util.List;

public interface CustomerService {
    public List<CustomerDTO> getAllCustomers();
    public CustomerDTO getCustomerById(long id);
    public CustomerDTO createCustomer(CustomerDTO customerDTO);
    public CustomerDTO updateCustomer(long id, CustomerDTO customerDTO);
    public void deleteCustomer(long id);
}
