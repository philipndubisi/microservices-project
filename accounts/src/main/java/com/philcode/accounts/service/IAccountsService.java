package com.philcode.accounts.service;

import com.philcode.accounts.dto.CustomerDto;

public interface IAccountsService {
    void createAccount(CustomerDto customerDTO);
    CustomerDto fetchAccountDetails(String mobileNumber);
    boolean updateAccountDetails(CustomerDto customerDTO);
    boolean deleteAccountDetails(String mobileNumber);

}
