package com.philcode.accounts.service.impl;

import com.philcode.accounts.constants.AccountsConstants;
import com.philcode.accounts.dto.AccountsDto;
import com.philcode.accounts.dto.CustomerDto;
import com.philcode.accounts.entity.Accounts;
import com.philcode.accounts.entity.Customer;
import com.philcode.accounts.exceptions.CustomerAlreadyExistsException;
import com.philcode.accounts.exceptions.ResourceNotFoundException;
import com.philcode.accounts.mapper.AccountsMapper;
import com.philcode.accounts.mapper.CustomerMapper;
import com.philcode.accounts.repository.AccountRepository;
import com.philcode.accounts.repository.CustomerRepository;
import com.philcode.accounts.service.IAccountsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountServiceImpl implements IAccountsService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    @Override
    public void createAccount(CustomerDto customerDTO) {
        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
        Optional<Customer> optionalCustomer= customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if (optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer with the given number already exists"
                    + customerDTO.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }
    @Override
    public CustomerDto fetchAccountDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDTO = CustomerMapper.maptToCustomerDTO(customer, new CustomerDto());
        customerDTO.setAccountsDTO(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        return customerDTO;
    }
    @Override
    public boolean updateAccountDetails(CustomerDto customerDTO) {
        boolean isUpdated = false;
        AccountsDto accountsDTO = customerDTO.getAccountsDTO();
        if (accountsDTO != null){
           Accounts accounts = accountRepository.findById(accountsDTO.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Accounts", "AccountNumber", accountsDTO.getAccountNumber().toString())
            );
           AccountsMapper.mapToAccounts(accountsDTO, accounts);
           accounts = accountRepository.save(accounts);
           Long customerId =  accounts.getCustomerId();
           Customer customer = customerRepository.findById(customerId).orElseThrow(
                   () -> new ResourceNotFoundException("Customer", "customerId", customerId.toString())
           );
           CustomerMapper.mapToCustomer(customerDTO, customer);
           customerRepository.save(customer);
           isUpdated = true;
        }
        return isUpdated;
    }
    @Override
    public boolean deleteAccountDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("customer", "mobileNumber", mobileNumber)
        );
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }


    public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }
}
