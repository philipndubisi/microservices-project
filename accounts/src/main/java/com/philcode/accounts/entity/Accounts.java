package com.philcode.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Accounts extends BaseEntity{
    @Id
    private Long accountNumber;
    private Long customerId;
    private String accountType;
    private String branchAddress;

    public Accounts() {
    }

    public Accounts(Long accountNumber, Long customerId, String accountType, String branchAddress) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.accountType = accountType;
        this.branchAddress = branchAddress;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }
}
