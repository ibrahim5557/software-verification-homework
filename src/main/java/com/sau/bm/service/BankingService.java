package com.sau.bm.service;

import com.sau.bm.model.Account;
import com.sau.bm.model.Customer;
import com.sau.bm.model.Loan;
import com.sau.bm.repository.AccountRepository;
import com.sau.bm.repository.CustomerRepository;
import com.sau.bm.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankingService {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final LoanRepository loanRepository;

    public List<Customer> getAllCustomers() { return customerRepository.findAll(); }
    public Customer saveCustomer(Customer c) { return customerRepository.save(c); }
    public void deleteCustomerById(Long id) { customerRepository.deleteById(id); }
    public Optional<Customer> getCustomerById(Long id){ return customerRepository.findById(id); }

    public List<Account> getAllAccounts() { return accountRepository.findAll(); }
    public Account saveAccount(Account a) { return accountRepository.save(a); }
    public void deleteAccountById(Long id) { accountRepository.deleteById(id); }
    public Optional<Account> getAccountById(Long id){ return accountRepository.findById(id); }

    public List<Loan> getAllLoans() { return loanRepository.findAll(); }
    public Loan saveLoan(Loan l) { return loanRepository.save(l); }
    public void deleteLoanById(Long id) { loanRepository.deleteById(id); }
    public Optional<Loan> getLoanById(Long id){ return loanRepository.findById(id); }
}
