package com.sau.bm.service;

import com.sau.bm.dtos.CustomerDTO;
import com.sau.bm.exception.ResourceAlreadyExistsException;
import com.sau.bm.model.Customer;
import com.sau.bm.exception.ErrorMessages;
import com.sau.bm.exception.ResourceNotFoundException;
import com.sau.bm.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ERROR_CUSTOMER_NOT_FOUND + ": " + id)).viewAsCustomerDTO();
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(Customer::viewAsCustomerDTO).toList();
    }

    @Override
    public CustomerDTO getCustomerById(long id) {
        return null;
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        if (customerRepository.findById(customerDTO.getId()).isPresent()) {
            throw new ResourceAlreadyExistsException(ErrorMessages.ERROR_CUSTOMER_ALREADY_EXIST + customerDTO.getId());
        }
        Customer customer = new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getCity());
        return customerRepository.save(customer).viewAsCustomerDTO();
    }

    public CustomerDTO updateCustomer(long id, CustomerDTO customerDTO) {
        // Öncelikle mevcut entity'yi repository'den çekiyoruz.
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + id));

        // DTO'dan gelen verilerle entity'yi güncelliyoruz.
        customer.setName(customerDTO.getName());
        customer.setAddress(customerDTO.getAddress());
        customer.setCity(customerDTO.getCity());

        // Güncellenmiş entity'yi kaydediyoruz ve DTO olarak döndürüyoruz.
        return customerRepository.save(customer).viewAsCustomerDTO();
    }


    public void deleteCustomer(long id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ERROR_CUSTOMER_NOT_FOUND + ": " + id));
        customerRepository.deleteById(id);
    }
}

